package com.okey.linkGenerator.service.impl;

import com.okey.linkGenerator.dto.UrlResponse;
import com.okey.linkGenerator.model.Invoice;
import com.okey.linkGenerator.repository.InvoiceRepository;
import com.okey.linkGenerator.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {

    @Value("${baseUrl}")
    private String BASE_URL;

    private final InvoiceRepository invoiceRepository;


    public UrlResponse generatePaymentLink(Long invoiceId) {
        Invoice invoice = invoiceRepository.findById(invoiceId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid invoice ID"));
        if (invoice.getPaymentLink() == null) {
            String link = BASE_URL + "/payment/" + UUID.randomUUID();
            invoice.setPaymentLink(link);
            return UrlResponse.builder().url(link).build();
        } else {
            return UrlResponse.builder().url(invoice.getPaymentLink()).build();
        }
    }
}
