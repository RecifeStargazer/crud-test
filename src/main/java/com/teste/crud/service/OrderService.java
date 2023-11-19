package com.teste.crud.service;

import com.teste.crud.dto.OrderDto;
import com.teste.crud.entity.Order;

import java.time.LocalDate;
import java.util.List;

public interface OrderService {
    public Order createOrder(OrderDto dto);

    public Order updateOrder(Order order);

    List<OrderDto> findAll();

    List<OrderDto> findByDate(LocalDate date);

    List<OrderDto> findOneById(Long orderNumber);
}
