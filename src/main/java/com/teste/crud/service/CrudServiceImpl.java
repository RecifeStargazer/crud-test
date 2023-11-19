package com.teste.crud.service;

import com.teste.crud.dto.ItemDto;
import com.teste.crud.dto.OrderDto;
import com.teste.crud.entity.Item;
import com.teste.crud.entity.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class CrudServiceImpl implements CrudService{

    private final OrderService orderService;
    private final ItemService itemService;

    @Autowired
    public CrudServiceImpl(OrderService orderService, ItemService itemService) {
        this.orderService = orderService;
        this.itemService = itemService;
    }

    @Override
    @Transactional
    public OrderDto order(OrderDto dto) {
        this.validateOrderSize(dto);
        BigDecimal totalValue = BigDecimal.ZERO;
        BigDecimal discount =  this.checkDiscount(dto.getItems());
        Order order = orderService.createOrder(dto);
        List<Item> items = new ArrayList<>();
        for(ItemDto item: dto.getItems()){
            this.validateQuantity(item);
            this.validateDate(item);
            itemService.validateControlNumber(item);
            items.add(itemService.createItem(item, order));
            totalValue = totalValue.add(item.getValue());
        }
        order.setItems(items);
        order.setTotalValues(totalValue.multiply(discount));
        orderService.updateOrder(order);
        return OrderDto.builder().items(dto.getItems()).totalValue(order.getTotalValues()).clientId(order.getClient().getId()).build();
    }

    private BigDecimal checkDiscount(List<ItemDto> dtos) {
        if(dtos.size()>5 && dtos.size()<10){
            return new BigDecimal("0.95");
        }if(dtos.size()==10){
            return new BigDecimal("0.90");
        }
        return BigDecimal.ONE;
    }

    private void validateOrderSize(OrderDto dto) {
        if(dto.getItems().isEmpty() || dto.getItems().size()>10){
            throw new IllegalArgumentException();
        }
    }

    private void validateDate(ItemDto item){
        if(item.getRegistrationDate()==null){
            item.setRegistrationDate(LocalDate.now());
        }
    }

    public void validateQuantity(ItemDto item){
        if(item.getQuantity()==null){
            item.setQuantity(1);
        }
    }

    @Override
    public List<OrderDto> getOrders(Map<String, String> params) {
        if(params.containsKey("orderNumber")){
            return this.orderService.findOneById(Long.valueOf(params.get("orderNumber")));
        }if(params.containsKey("registationDate")){
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-d");
            LocalDate localDate = LocalDate.parse(params.get("registationDate"), formatter);
            return this.orderService.findByDate(localDate);
        }
        return this.orderService.findAll();
    }
}
