package com.ankilla.order.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;

@Data
@NoRepositoryBean
@AllArgsConstructor
public class OrderDTO {

    private Integer orderId;
    private List<FoodItemDTO> foodItemsList;
    private Restaurant restaurant;
    private UserDTO userDTO;

}
