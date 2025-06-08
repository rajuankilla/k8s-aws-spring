import { FoodItem } from "../../shared/modals/FoodItem";
import { Restaurant } from "../../shared/modals/Restaurant";


export interface OrderDTO{
    foodItemsList?: FoodItem[] ;
     userId?: number;
     restaurant?: Restaurant;
}