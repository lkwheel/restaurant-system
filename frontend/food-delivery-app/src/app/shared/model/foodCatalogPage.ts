import { FoodItem } from "./foodItem";
import { Restaurant } from "./restaurant";

export interface FoodCatalogPage {
    foodItemList: FoodItem[];
    restaurant: Restaurant;
}