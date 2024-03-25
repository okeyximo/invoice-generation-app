package com.okey.linkGenerator.service;

import com.okey.linkGenerator.dto.InvoiceRequest;
import com.okey.linkGenerator.dto.InvoiceResponse;
import com.okey.linkGenerator.dto.UrlResponse;

import java.util.UUID;

public interface InvoiceService {
    InvoiceResponse createInvoice(InvoiceRequest invoiceRequest);

    UrlResponse generateInvoiceLink(Long invoiceId);

    InvoiceResponse viewInvoice(UUID invoiceId);
}
