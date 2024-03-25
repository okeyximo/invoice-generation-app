package com.okey.linkGenerator.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Invoice extends BaseEntity{

    private String invoiceNumber;
    private BigDecimal totalAmount;
    private LocalDate dueDate;
    private String invoiceLink;
    private String paymentLink;
    @OneToMany(mappedBy = "invoice")
    private List<InvoiceItem> invoiceItems;
}
