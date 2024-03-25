package com.okey.linkGenerator.service.impl;

import com.okey.linkGenerator.dto.InvoiceItemDto;
import com.okey.linkGenerator.dto.InvoiceRequest;
import com.okey.linkGenerator.dto.InvoiceResponse;
import com.okey.linkGenerator.dto.UrlResponse;
import com.okey.linkGenerator.model.Invoice;
import com.okey.linkGenerator.model.InvoiceItem;
import com.okey.linkGenerator.repository.InvoiceRepository;
import com.okey.linkGenerator.service.InvoiceService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class InvoiceServiceImpl implements InvoiceService {
    @Value("${baseUrl}")
    private String BASE_URL;
    private final InvoiceRepository invoiceRepository;


    @Override
    public InvoiceResponse createInvoice(InvoiceRequest invoiceRequest) {
        List<InvoiceItem> invoiceItems = invoiceRequest.getInvoiceItems().stream()
                .map(itemDto -> {
                    InvoiceItem item = new InvoiceItem();
                    item.setDescription(itemDto.getDescription());
                    item.setAmount(itemDto.getAmount());
                    return item;
                })
                .collect(Collectors.toList());
        BigDecimal totalAmount = invoiceItems.stream()
                .map(InvoiceItem::getAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        Invoice invoice = Invoice.builder()
                .totalAmount(totalAmount)
                .invoiceNumber(invoiceRequest.getInvoiceNumber())
                .invoiceItems(invoiceItems)
                .build();

        invoiceRepository.save(invoice);

        List<InvoiceItemDto> invoiceItemDtos = invoice.getInvoiceItems().stream()
                .map(invoiceItem -> InvoiceItemDto.builder()
                        .description(invoiceItem.getDescription())
                        .amount(invoiceItem.getAmount())
                        .build())
                .collect(Collectors.toList());

        return InvoiceResponse.builder()
                .id(invoice.getId())
                .invoiceNumber(invoice.getInvoiceNumber())
                .totalAmount(invoice.getTotalAmount())
                .dueDate(invoice.getDueDate())
                .invoiceItems(invoiceItemDtos)
                .build();
    }

    @Override
    public UrlResponse generateInvoiceLink(Long invoiceId) {
        Invoice invoice = invoiceRepository.findById(invoiceId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid invoice ID"));

        if (invoice.getInvoiceLink() == null) {
            String link = BASE_URL + "invoice/" + UUID.randomUUID();
            invoice.setPaymentLink(link);
            return UrlResponse.builder().url(link).build();
        } else {
            return UrlResponse.builder().url(invoice.getInvoiceLink()).build();
        }
    }
}
