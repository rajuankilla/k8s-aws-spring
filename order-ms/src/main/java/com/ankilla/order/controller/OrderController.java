package com.ankilla.order.controller;

import com.ankilla.order.dto.OrderDTO;
import com.ankilla.order.dto.OrderDTOFromFE;
import com.ankilla.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/order")
@CrossOrigin
public class OrderController {

//    @Autowired
    OrderService orderService;

    public OrderController (OrderService orderService){
        this.orderService=orderService;
    }

    @PostMapping("/saveOrder")
    public ResponseEntity<OrderDTO> saveOrder(@RequestBody OrderDTOFromFE orderDetails) {
        OrderDTO orderDto = orderService.saveOrderInDb(orderDetails);
        return new ResponseEntity<>(orderDto, HttpStatus.OK);
    }
}
