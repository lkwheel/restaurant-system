import { Restaurant } from "./restaurant";

export interface FoodItem {
    id: number;
    itemName: string;
    itemDescription: string;
    isVegetarian: boolean;
    price: number;
    restaurantId: number;
    quantity: number;
}