package com.okey.linkGenerator.service;

import com.okey.linkGenerator.dto.InvoiceRequest;
import com.okey.linkGenerator.dto.InvoiceResponse;
import com.okey.linkGenerator.dto.UrlResponse;

public interface InvoiceService {
    InvoiceResponse createInvoice(InvoiceRequest invoiceRequest);

    UrlResponse generateInvoiceLink(Long invoiceId);
}
