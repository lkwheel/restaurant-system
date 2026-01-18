package com.codiecode.common.dto;

public class FoodItemDTO {

    private Long id;
    private String itemName;
    private String itemDescription;
    private boolean isVegetarian;
    private Double price;
    private Long restaurantId;
    private Integer quantity;

    public FoodItemDTO() {
    }

    public FoodItemDTO(Long id, String itemName, String itemDescription, boolean isVegetarian, Double price, Long restaurantId, Integer quantity) {
        this.id = id;
        this.itemName = itemName;
        this.itemDescription = itemDescription;
        this.isVegetarian = isVegetarian;
        this.price = price;
        this.restaurantId = restaurantId;
        this.quantity = quantity;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemDescription() {
        return itemDescription;
    }

    public void setItemDescription(String itemDescription) {
        this.itemDescription = itemDescription;
    }

    public boolean isVegetarian() {
        return isVegetarian;
    }

    public void setVegetarian(boolean vegetarian) {
        isVegetarian = vegetarian;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Long getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(Long restaurantId) {
        this.restaurantId = restaurantId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
