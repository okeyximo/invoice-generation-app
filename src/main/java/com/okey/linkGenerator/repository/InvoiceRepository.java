package com.okey.linkGenerator.repository;

import com.okey.linkGenerator.model.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InvoiceRepository extends JpaRepository<Invoice, Long> {
}