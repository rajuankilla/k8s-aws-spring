package com.ankilla.restaurentlisting.service;


import com.ankilla.restaurentlisting.dto.RestaurantDTO;
import com.ankilla.restaurentlisting.entity.Restaurant;
import com.ankilla.restaurentlisting.mapper.RestaurantMapper;
import com.ankilla.restaurentlisting.repository.RestaurantRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RestaurantService {

    private static final Logger logger = LoggerFactory.getLogger(RestaurantService.class);

    @Autowired
    RestaurantRepo restaurantRepo;

    private RestaurantMapper restaurantMapper;

    public RestaurantService(RestaurantMapper restaurantMapper){
        this.restaurantMapper=restaurantMapper;
    }

    public List<RestaurantDTO> findAllRestaurants() {

        List<Restaurant> restaurants = restaurantRepo.findAll();
        restaurants.forEach(System.out::println);
        // map list to dto
        return restaurants.stream().map(restaurant -> restaurantMapper.mapRestaurantToRestaurantDTO(restaurant)).toList();

    }

    public RestaurantDTO addRestaurantInDB(RestaurantDTO restaurantDTO) {
        System.out.println("After converting to entity : "+restaurantMapper.mapRestaurantDTOToRestaurant(restaurantDTO).toString());
        Restaurant restaurant = restaurantRepo.save(restaurantMapper.mapRestaurantDTOToRestaurant(restaurantDTO));
        return restaurantMapper.mapRestaurantToRestaurantDTO(restaurant);
    }

    public ResponseEntity<RestaurantDTO> findById(Integer restaurantId) {
        Optional<Restaurant> restaurant = restaurantRepo.findById(restaurantId);
        return restaurant.map(value -> new ResponseEntity<>(restaurantMapper.mapRestaurantToRestaurantDTO(value), HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(null, HttpStatus.NOT_FOUND));
    }

}
