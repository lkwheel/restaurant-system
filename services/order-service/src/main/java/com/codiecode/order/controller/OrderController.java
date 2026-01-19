package com.codiecode.order.controller;

import com.codiecode.common.dto.OrderDTO;
import com.codiecode.order.service.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping()
    public ResponseEntity<List<OrderDTO>> fetchAllOrders() {
        List<OrderDTO> orderDTOList = orderService.fetchAll();
        return new ResponseEntity<>(orderDTOList, HttpStatus.OK);
    }

    @PostMapping("/saveOrder")
    public ResponseEntity<OrderDTO> addOrder(@RequestBody OrderDTO order) {
        OrderDTO orderSaved = orderService.addOrder(order);
        return new ResponseEntity<>(orderSaved, HttpStatus.CREATED);
    }
}
