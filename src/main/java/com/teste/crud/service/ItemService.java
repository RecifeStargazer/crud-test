package com.teste.crud.service;

import com.teste.crud.dto.ItemDto;
import com.teste.crud.entity.Item;
import com.teste.crud.entity.Order;

import java.time.LocalDate;
import java.util.List;

public interface ItemService {
    public Item createItem(ItemDto itemDto, Order order);

    void validateControlNumber(ItemDto item);

    List<ItemDto> getItemDtos(List<Item> items);

    List<Item> findByRegistrationDate(LocalDate date);
}
