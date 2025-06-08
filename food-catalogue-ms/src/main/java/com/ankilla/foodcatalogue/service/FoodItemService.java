package com.ankilla.foodcatalogue.service;

import com.ankilla.foodcatalogue.dto.FoodCataloguePage;
import com.ankilla.foodcatalogue.dto.FoodItemDTO;
import com.ankilla.foodcatalogue.dto.Restaurant;
import com.ankilla.foodcatalogue.entity.FoodItem;
import com.ankilla.foodcatalogue.mapper.FoodItemMapper;
import com.ankilla.foodcatalogue.repo.FoodItemRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@Service
public class FoodItemService {

    private static final Logger logger = LoggerFactory.getLogger(FoodItemService.class);

    @Autowired
    FoodItemRepo foodItemRepo;

    @Autowired
    RestTemplate restTemplate;

    private FoodItemMapper foodItemMapper;

    public FoodItemService(FoodItemMapper foodItemMapper){
        this.foodItemMapper=foodItemMapper;
    }


    public List<FoodItemDTO> findAllFoodItems() {
        List<FoodItem> foodItemList = foodItemRepo.findAll();
        foodItemList.forEach(System.out::println);

        return foodItemList.stream().map(foodItem -> foodItemMapper.mapFoodItemToFoodItemDTO(foodItem)).toList();
    }

    public FoodItemDTO addRestaurantInDB(FoodItemDTO foodItemDTO) {
        FoodItem foodItem = foodItemRepo.save(foodItemMapper.mapFoodItemDTOToFoodItem(foodItemDTO));
        return foodItemMapper.mapFoodItemToFoodItemDTO(foodItem);
    }

    public ResponseEntity<FoodItemDTO> findById(Integer id) {
        Optional<FoodItem> foodItem = foodItemRepo.findById(id);
        return foodItem.map(item -> new ResponseEntity<>(foodItemMapper.mapFoodItemToFoodItemDTO(item), HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(null, HttpStatus.NOT_FOUND));
    }

    public FoodCataloguePage fetchFoodCatalogurePageDetails(Integer restaurantId) {
        List<FoodItem> foodItemList = fetchFoodItemList(restaurantId);
        Restaurant restaurant = fetchRestaurantDetailsFromRestaurantMS(restaurantId);
        return createFoodCataloguePage(foodItemList,restaurant);
    }


    private FoodCataloguePage createFoodCataloguePage(List<FoodItem> foodItemList, Restaurant restaurant) {

        FoodCataloguePage foodCataloguePage = new FoodCataloguePage();
        foodCataloguePage.setFoodItemsList(foodItemList);
        foodCataloguePage.setRestaurant(restaurant);
        return foodCataloguePage;
    }

    private Restaurant fetchRestaurantDetailsFromRestaurantMS(Integer restaurantId) {
       return restTemplate.getForObject("http://RESTAURENTLISTING/restaurant/fetchById/"+restaurantId, Restaurant.class);
    }

    private List<FoodItem> fetchFoodItemList(Integer restaurantId) {
        return foodItemRepo.findByRestaurantId(restaurantId);
    }
}
