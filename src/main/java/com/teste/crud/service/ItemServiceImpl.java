package com.teste.crud.service;

import com.teste.crud.dto.ItemDto;
import com.teste.crud.entity.Item;
import com.teste.crud.entity.Order;
import com.teste.crud.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class ItemServiceImpl implements ItemService{

    private final ItemRepository repository;

    @Autowired
    public ItemServiceImpl(ItemRepository repository) {
        this.repository = repository;
    }

    @Override
    public Item createItem(ItemDto itemDto, Order order) {
        return repository.save(Item.builder()
                .order(order)
                .value(itemDto.getValue())
                .controlNumber(itemDto.getControlNumber())
                .productName(itemDto.getProductName())
                .quantity(itemDto.getQuantity())
                .registrationDate(itemDto.getRegistrationDate())
                .build());
    }

    @Override
    public void validateControlNumber(ItemDto item){
        if(repository.findByControlNumber(item.getControlNumber())!=null){
            throw new IllegalArgumentException();
        };
    }

    @Override
    public List<ItemDto> getItemDtos(List<Item> items) {
        List<ItemDto> result = new ArrayList<>();
        for(Item item: items){
            result.add(ItemDto.builder()
                    .controlNumber(item.getControlNumber())
                    .registrationDate(item.getRegistrationDate())
                    .productName(item.getProductName())
                    .quantity(item.getQuantity())
                    .value(item.getValue())
                    .build());
        }
        return result;
    }

    @Override
    public List<Item> findByRegistrationDate(LocalDate date) {
        return repository.findByRegistrationDate(date);
    }
}
