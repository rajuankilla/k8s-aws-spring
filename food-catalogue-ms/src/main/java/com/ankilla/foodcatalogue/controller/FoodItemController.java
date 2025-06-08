package com.ankilla.foodcatalogue.controller;

import com.ankilla.foodcatalogue.dto.FoodCataloguePage;
import com.ankilla.foodcatalogue.dto.FoodItemDTO;
import com.ankilla.foodcatalogue.service.FoodItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/foodCatalogue")
@CrossOrigin
public class FoodItemController {

//    @Autowired
    FoodItemService foodItemService;

    public FoodItemController(FoodItemService foodItemService){
        this.foodItemService=foodItemService;
    }

    @GetMapping("/fetchAllFoodItems")
    public ResponseEntity<List<FoodItemDTO>> fetchAllFoodItems() {
        List<FoodItemDTO> allFoodItems = foodItemService.findAllFoodItems();
        return new ResponseEntity<>(allFoodItems, HttpStatus.OK);
    }


    @PostMapping(value = "/addFoodItem")
    public ResponseEntity<FoodItemDTO> saveFoodItem(@RequestBody FoodItemDTO foodItemDTO) {

        System.out.println("----payload " + foodItemDTO);
        FoodItemDTO foodItemAdded = foodItemService.addRestaurantInDB(foodItemDTO);
        return new ResponseEntity<>(foodItemAdded, HttpStatus.CREATED);
    }

    @GetMapping("fetchRestaurantAndFoodItemsById/{restaurantId}")
    public ResponseEntity<FoodCataloguePage> findFoodItemById(@PathVariable Integer restaurantId) {
        FoodCataloguePage foodCataloguePage = foodItemService.fetchFoodCatalogurePageDetails(restaurantId);
        return new ResponseEntity<>(foodCataloguePage, HttpStatus.OK);
    }


}
