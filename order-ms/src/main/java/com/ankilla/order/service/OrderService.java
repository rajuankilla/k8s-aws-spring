package com.ankilla.order.service;

import com.ankilla.order.dto.OrderDTO;
import com.ankilla.order.dto.OrderDTOFromFE;
import com.ankilla.order.dto.UserDTO;
import com.ankilla.order.entity.Order;
import com.ankilla.order.mapper.OrderMapper;
import com.ankilla.order.repo.OrderRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class OrderService {

    @Autowired
    OrderRepo orderRepo;

    private OrderMapper orderMapper;

    public OrderService(OrderMapper orderMapper){
        this.orderMapper = orderMapper;
    }

    @Autowired
    SequenceGenerator sequenceGenerator;

    @Autowired
    RestTemplate restTemplate;

    public OrderDTO saveOrderInDb(OrderDTOFromFE orderDetails) {
        Integer orderId = sequenceGenerator.generateNextOrderId();
        UserDTO userDTO = fetchOrderDetailsFromUserId(orderDetails.getUserId());
        Order orderToBeSaved = new Order(orderId,orderDetails.getFoodItemDTOList(),orderDetails.getRestaurant(), userDTO);
        orderRepo.save(orderToBeSaved);
        return orderMapper.mapOrderToOrderDTO(orderToBeSaved);
    }

    private UserDTO fetchOrderDetailsFromUserId(Integer userId) {
        return restTemplate.getForObject("http://USERINFORMATION/user/fetchById/"+userId, UserDTO.class);
    }
}
