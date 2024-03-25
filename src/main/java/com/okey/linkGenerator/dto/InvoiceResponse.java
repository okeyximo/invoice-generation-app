package com.okey.linkGenerator.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
@Data
@Builder
public class InvoiceResponse {

    private long id;
    private String invoiceNumber;
    private LocalDate dueDate;
    private List<InvoiceItemDto> invoiceItems;
    private BigDecimal totalAmount;
}
