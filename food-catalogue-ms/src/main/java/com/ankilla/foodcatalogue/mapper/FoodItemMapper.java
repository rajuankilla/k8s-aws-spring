package com.ankilla.foodcatalogue.mapper;

import com.ankilla.foodcatalogue.dto.FoodItemDTO;
import com.ankilla.foodcatalogue.entity.FoodItem;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface FoodItemMapper {

//    FoodItemMapper INSTANCE = Mappers.getMapper(FoodItemMapper.class);

    FoodItem mapFoodItemDTOToFoodItem(FoodItemDTO foodItemDTO);
    FoodItemDTO mapFoodItemToFoodItemDTO(FoodItem foodItem);
}
