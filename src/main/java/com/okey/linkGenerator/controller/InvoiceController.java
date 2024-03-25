package com.okey.linkGenerator.controller;

import com.okey.linkGenerator.dto.InvoiceRequest;
import com.okey.linkGenerator.dto.InvoiceResponse;
import com.okey.linkGenerator.dto.UrlResponse;
import com.okey.linkGenerator.service.InvoiceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api/invoices")
@RequiredArgsConstructor
public class InvoiceController {

    private final InvoiceService invoiceService;

    @PostMapping
    public ResponseEntity<InvoiceResponse> createInvoice(@RequestBody InvoiceRequest invoiceRequest) {
        return new ResponseEntity<>(invoiceService.createInvoice(invoiceRequest), HttpStatus.CREATED);
    }

    @GetMapping("/{id}/link")
    public ResponseEntity<UrlResponse> generateInvoiceLink(@PathVariable Long id) {
        return ResponseEntity.ok(invoiceService.generateInvoiceLink(id));
    }

    @GetMapping("/{invoiceId}")
    public ResponseEntity<InvoiceResponse> viewInvoice(@PathVariable UUID invoiceId){
        return ResponseEntity.ok(invoiceService.viewInvoice(invoiceId));
    }
}
