package com.okey.linkGenerator.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
@Builder
public class InvoiceRequest {
    private String invoiceNumber;
    private List<InvoiceItemDto> invoiceItems;
}
