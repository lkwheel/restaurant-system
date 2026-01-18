package com.codiecode.foodcatalog.dto;

import com.codiecode.common.dto.RestaurantDTO;
import com.codiecode.foodcatalog.entity.FoodItem;

import java.util.List;

public class FoodCatalogPage {

    private List<FoodItem> foodItemList;
    private RestaurantDTO restaurant;

    public FoodCatalogPage() {
    }

    public FoodCatalogPage(List<FoodItem> foodItemList, RestaurantDTO restaurant) {
        this.foodItemList = foodItemList;
        this.restaurant = restaurant;
    }

    public List<FoodItem> getFoodItemList() {
        return foodItemList;
    }

    public void setFoodItemList(List<FoodItem> foodItemList) {
        this.foodItemList = foodItemList;
    }

    public RestaurantDTO getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(RestaurantDTO restaurant) {
        this.restaurant = restaurant;
    }
}
