package com.teste.crud.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.validation.constraints.NotEmpty;
import java.math.BigDecimal;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderDto {
    @NonNull
    @JsonProperty("clientId")
    private Long clientId;
    @NotEmpty
    private List<ItemDto> items;
    private BigDecimal totalValue;
}
