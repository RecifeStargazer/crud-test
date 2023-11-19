package com.teste.crud.service;

import com.teste.crud.dto.ItemDto;
import com.teste.crud.dto.OrderDto;
import com.teste.crud.entity.Client;
import com.teste.crud.entity.Item;
import com.teste.crud.entity.Order;
import com.teste.crud.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;

@Service
public class OrderServiceImpl implements OrderService{

    private final OrderRepository repository;
    private final ClientService clientService;
    private final ItemService itemService;

    @Autowired
    public OrderServiceImpl(OrderRepository repository, ClientService clientService, ItemService itemService) {
        this.repository = repository;
        this.clientService = clientService;
        this.itemService = itemService;
    }

    @Override
    public Order createOrder(OrderDto dto) {
        Client client = clientService.findClientById(dto.getClientId());
        if(client==null){
            throw new IllegalArgumentException();
        }
        Order result = repository.save(Order.builder().client(client).build());
        client.getOrders().add(result);
        clientService.updateClient(client);
        return result;
    }

    @Override
    public Order updateOrder(Order order) {
        return repository.save(order);
    }

    @Override
    public List<OrderDto> findAll() {
        List<Order> orders = repository.findAll();
        List<OrderDto> result = new ArrayList<>();
        for(Order order: orders){
            List<ItemDto> items = itemService.getItemDtos(order.getItems());
            result.add(OrderDto.builder()
                    .clientId(order.getClient().getId())
                    .totalValue(order.getTotalValues())
                    .items(items)
                    .build());
        }
        return result;
    }

    @Override
    public List<OrderDto> findByDate(LocalDate date){
        Set<OrderDto> result = new HashSet<>();
        List<Item>  itemEntities = this.itemService.findByRegistrationDate(date);
        for(Item item: itemEntities){
            result.add(OrderDto.builder()
                    .clientId(item.getOrder().getClient().getId())
                    .items(this.itemService.getItemDtos(item.getOrder().getItems()))
                    .totalValue(item.getOrder().getTotalValues())
                    .build());
        }
        return new ArrayList<>(result);
    }

    @Override
    public List<OrderDto> findOneById(Long orderNumber) {
        List<OrderDto> result = new ArrayList<>();
        Order order = this.repository.findById(orderNumber).get();
        result.add(OrderDto.builder()
                .clientId(order.getClient().getId())
                .items(this.itemService.getItemDtos(order.getItems()))
                .totalValue(order.getTotalValues())
                .build());
        return result;
    }
}
