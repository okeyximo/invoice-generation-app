package com.okey.linkGenerator.repository;

import com.okey.linkGenerator.dto.InvoiceResponse;
import com.okey.linkGenerator.model.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface InvoiceRepository extends JpaRepository<Invoice, Long> {
    Invoice findByInvoiceId(UUID invoiceId);
}