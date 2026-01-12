package com.codiecode.restaurantlsting.dto;

public class RestaurantDTO {
    private Long id;
    private String name;
    private String city;
    private String address;
    private String restaurantDescription;



    public RestaurantDTO() {
    }

    public RestaurantDTO(Long id, String name, String city, String address, String restaurantDescription) {
        this.id = id;
        this.name = name;
        this.city = city;
        this.address = address;
        this.restaurantDescription = restaurantDescription;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getRestaurantDescription() {
        return restaurantDescription;
    }

    public void setRestaurantDescription(String restaurantDescription) {
        this.restaurantDescription = restaurantDescription;
    }
}
