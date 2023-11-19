package com.teste.crud.dto;

import lombok.Builder;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDate;
@Data
@Builder
public class ItemDto {
    private Long controlNumber;
    private LocalDate registrationDate;
    private String productName;
    private BigDecimal value;
    private Integer quantity;
}
