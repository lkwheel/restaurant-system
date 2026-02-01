import { FoodItem } from "./foodItem";
import { Restaurant } from "./restaurant";

export interface Order {
    foodItemList?: FoodItem[];
    userId?: number;
    restaurant?: Restaurant;
}