package com.codiecode.common.dto;

import java.util.List;

public class OrderDTO {
    private Integer orderId;
    private List<FoodItemDTO> foodItemDTOList;
    private Long userId;
    private RestaurantDTO restaurant;

    public OrderDTO() {
    }

    public OrderDTO(Integer orderId, List<FoodItemDTO> foodItemDTOList, Long userId, RestaurantDTO restaurant) {
        this.orderId = orderId;
        this.foodItemDTOList = foodItemDTOList;
        this.userId = userId;
        this.restaurant = restaurant;
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

    public void setFoodItemDTOList(List<FoodItemDTO> foodItemsList) {
        this.foodItemDTOList = foodItemsList;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public RestaurantDTO getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(RestaurantDTO restaurant) {
        this.restaurant = restaurant;
    }
}