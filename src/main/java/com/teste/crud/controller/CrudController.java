package com.teste.crud.controller;

import com.teste.crud.dto.OrderDto;
import com.teste.crud.service.CrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/order")
public class CrudController {
    private final CrudService service;
    @Autowired
    public CrudController(CrudService service) {
        this.service = service;
    }

    @PostMapping
    public OrderDto order(@Valid @RequestBody OrderDto dto) {
        return service.order(dto);
    }

    @GetMapping
    public List<OrderDto> getOrders(@RequestParam Map<String,String> params){
        return service.getOrders(params);
    };
}
