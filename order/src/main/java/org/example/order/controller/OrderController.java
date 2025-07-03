package org.example.order.controller;


import org.example.order.dto.OrderResponseDto;
import org.example.order.entity.Order;
import org.example.order.service.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {

    public OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderResponseDto> getOrder(@PathVariable int id) {
        Order order = orderService.getOrderById(id);
        if (order == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(orderService.orderToOrderResponseDto(order));
    }

    @PostMapping
    public ResponseEntity<OrderResponseDto> createOrder(@RequestBody Order order) {
        Order savedOrder = orderService.saveOrder(order);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(orderService.orderToOrderResponseDto(savedOrder));
    }

    @GetMapping
    public ResponseEntity<List<OrderResponseDto>> getAllOrders() {
        return ResponseEntity.ok(orderService.getAllOrders().stream()
                .map(orderService::orderToOrderResponseDto)
                .toList());
    }
    
}
