package com.codiecode.order.entity;

import com.codiecode.common.dto.FoodItemDTO;
import com.codiecode.common.dto.RestaurantDTO;
import com.codiecode.common.dto.UserDTO;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "order")
public class Order {
    @Id
    private Integer orderId;
    private List<FoodItemDTO> foodItemDTOList;
    private RestaurantDTO restaurant;
    private UserDTO user;

    public Order() {
    }

    public Order(Integer orderId, List<FoodItemDTO> foodItemDTOList, RestaurantDTO restaurant, UserDTO user) {
        this.orderId = orderId;
        this.foodItemDTOList = foodItemDTOList;
        this.restaurant = restaurant;
        this.user = user;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public List<FoodItemDTO> getFoodItemDTOList() {
        return foodItemDTOList;
    }

    public void setFoodItemDTOList(List<FoodItemDTO> foodItemDTOList) {
        this.foodItemDTOList = foodItemDTOList;
    }

    public RestaurantDTO getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(RestaurantDTO restaurant) {
        this.restaurant = restaurant;
    }

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }
}
