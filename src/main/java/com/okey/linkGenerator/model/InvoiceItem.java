package com.okey.linkGenerator.model;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class InvoiceItem extends BaseEntity{

    private String description;
    private BigDecimal amount;

    @ManyToOne
    private Invoice invoice;
}