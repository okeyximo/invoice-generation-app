package com.okey.linkGenerator.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class InvoiceItemDto {
    private String description;
    private BigDecimal amount;
}
