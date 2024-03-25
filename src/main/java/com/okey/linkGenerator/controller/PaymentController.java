package com.okey.linkGenerator.controller;

import com.okey.linkGenerator.dto.UrlResponse;
import com.okey.linkGenerator.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/payments")
@RequiredArgsConstructor
public class PaymentController {
    private final PaymentService paymentService;

    @GetMapping("/{invoiceId}/link")
    public ResponseEntity<UrlResponse> generatePaymentLink(@PathVariable Long invoiceId) {
        return ResponseEntity.ok(paymentService.generatePaymentLink(invoiceId));
    }

}
