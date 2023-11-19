package com.teste.crud.service;

import com.teste.crud.dto.OrderDto;

import java.util.List;
import java.util.Map;

public interface CrudService {
    public OrderDto order(OrderDto dto);

    List<OrderDto> getOrders(Map<String, String> params);
}
