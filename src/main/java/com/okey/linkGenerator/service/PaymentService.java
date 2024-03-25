package com.okey.linkGenerator.service;

import com.okey.linkGenerator.dto.UrlResponse;

public interface PaymentService {
    UrlResponse generatePaymentLink(Long invoiceId);
}
