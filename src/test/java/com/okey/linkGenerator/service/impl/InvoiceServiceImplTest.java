package com.okey.linkGenerator.service.impl;

import static org.junit.jupiter.api.Assertions.*;

import com.okey.linkGenerator.dto.InvoiceItemDto;
import com.okey.linkGenerator.dto.InvoiceRequest;
import com.okey.linkGenerator.dto.InvoiceResponse;
import com.okey.linkGenerator.model.Invoice;
import com.okey.linkGenerator.repository.InvoiceRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.util.ReflectionTestUtils;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class InvoiceServiceImplTest {

    @Mock
    private InvoiceRepository invoiceRepository;

    @InjectMocks
    private InvoiceServiceImpl invoiceService;

    @BeforeEach
    void setUp() {
        ReflectionTestUtils.setField(invoiceService, "BASE_URL", "http://example.com");
    }

    @Test
    void testCreateInvoice() {
        InvoiceRequest invoiceRequest = buildInvoiceRequest();

        when(invoiceRepository.save(any(Invoice.class))).thenAnswer(invocationOnMock -> invocationOnMock.getArgument(0));

        InvoiceResponse invoiceResponse = invoiceService.createInvoice(invoiceRequest);

        assertEquals(invoiceRequest.getInvoiceNumber(), invoiceResponse.getInvoiceNumber());
        assertEquals(BigDecimal.valueOf(30.0), invoiceResponse.getTotalAmount());
        assertEquals(invoiceRequest.getInvoiceItems().size(), invoiceResponse.getInvoiceItems().size());
    }

    @Test
    void testGenerateInvoiceLink_NewLink() {
        Long invoiceId = 1L;
        Invoice invoice = Invoice.builder().build();

        when(invoiceRepository.findById(invoiceId)).thenReturn(Optional.of(invoice));

        String invoiceLink = invoiceService.generateInvoiceLink(invoiceId);

        assertEquals(invoice.getPaymentLink(), invoiceLink);
    }

    @Test
    void testGenerateInvoiceLink_ExistingLink() {
        Long invoiceId = 1L;
        String existingLink = "http://example.com/invoice/" + UUID.randomUUID();
        Invoice invoice = Invoice.builder().invoiceLink(existingLink).build();

        when(invoiceRepository.findById(invoiceId)).thenReturn(Optional.of(invoice));

        String invoiceLink = invoiceService.generateInvoiceLink(invoiceId);

        assertEquals(existingLink, invoiceLink);
    }

    @Test
    void testGenerateInvoiceLink_InvalidInvoiceId() {
        Long invalidInvoiceId = 99L;

        when(invoiceRepository.findById(invalidInvoiceId)).thenReturn(Optional.empty());

        assertThrows(IllegalArgumentException.class, () -> invoiceService.generateInvoiceLink(invalidInvoiceId));
    }

    private InvoiceRequest buildInvoiceRequest() {
        InvoiceItemDto itemDto1 = InvoiceItemDto.builder().description("Item 1").amount(BigDecimal.valueOf(10.0)).build();
        InvoiceItemDto itemDto2 = InvoiceItemDto.builder().description("Item 2").amount((BigDecimal.valueOf(20.0))).build();
        List<InvoiceItemDto> invoiceItems = Arrays.asList(itemDto1, itemDto2);

        return InvoiceRequest.builder()
                .invoiceNumber("INV001")
                .invoiceItems(invoiceItems)
                .build();
    }
}