package com.ankilla.restaurentlisting.controller;

import com.ankilla.restaurentlisting.dto.RestaurantDTO;
import com.ankilla.restaurentlisting.service.RestaurantService;
import org.apache.coyote.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/restaurant")
@CrossOrigin
public class RestaurantController {

//    @Autowired
    RestaurantService restaurantService;

    public RestaurantController(RestaurantService restaurantService){
        this.restaurantService=restaurantService;
    }


    private static final Logger logger = LoggerFactory.getLogger(RestaurantController.class);

    @GetMapping("/fetchAllRestaurants")
    public ResponseEntity<List<RestaurantDTO>> fetAllRestaurants(){
        logger.info("Fetching all records for fetchAllRestaurants");
        List<RestaurantDTO> allRestaurants = restaurantService.findAllRestaurants();
        allRestaurants.forEach(System.out::println);
        return new ResponseEntity<>(allRestaurants, HttpStatus.OK);
    }


    @PostMapping(value = "/addRestaurant")
    public ResponseEntity<RestaurantDTO> saveRestaurant(@RequestBody RestaurantDTO restaurantDTO){

        System.out.println("----payload " + restaurantDTO);


        RestaurantDTO restaurantAdded  = restaurantService.addRestaurantInDB(restaurantDTO);
        return new ResponseEntity<>(restaurantAdded, HttpStatus.CREATED);
    }

    @GetMapping("fetchById/{id}")
    public ResponseEntity<RestaurantDTO> findRestaurantById(@PathVariable Integer id){

      return  restaurantService.findById(id);
    }


}
