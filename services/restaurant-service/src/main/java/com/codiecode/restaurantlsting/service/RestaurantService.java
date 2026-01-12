package com.codiecode.restaurantlsting.service;

import com.codiecode.restaurantlsting.dto.RestaurantDTO;
import com.codiecode.restaurantlsting.entity.Restaurant;
import com.codiecode.restaurantlsting.exception.ResourceNotFoundException;
import com.codiecode.restaurantlsting.repo.RestaurantRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RestaurantService {

    private final RestaurantRepo restaurantRepo;

    public RestaurantService(RestaurantRepo restaurantRepo) {
        this.restaurantRepo = restaurantRepo;
    }

    public List<RestaurantDTO> findAllRestaurants() {
        List<Restaurant> restaurants = restaurantRepo.findAll();
        return restaurants.stream()
                .map(restaurant -> new RestaurantDTO(
                        restaurant.getId(),
                        restaurant.getName(),
                        restaurant.getCity(),
                        restaurant.getAddress(),
                        restaurant.getRestaurantDescription()
                ))
                .toList();
    }

    public RestaurantDTO addRestaurant(RestaurantDTO restaurant) {
        Restaurant restaurantToAdd = new Restaurant(
                restaurant.getName(),
                restaurant.getCity(),
                restaurant.getAddress(),
                restaurant.getRestaurantDescription()
        );
        Restaurant addedRestaurant = restaurantRepo.save(restaurantToAdd);
        return new RestaurantDTO(
                addedRestaurant.getId(),
                addedRestaurant.getName(),
                addedRestaurant.getCity(),
                addedRestaurant.getAddress(),
                addedRestaurant.getRestaurantDescription()
        );
    }

    public RestaurantDTO fetchRestaurantById(Long id) {
        return restaurantRepo.findById(id)
                .map(restaurant -> {
                    return new RestaurantDTO(
                            restaurant.getId(),
                            restaurant.getName(),
                            restaurant.getCity(),
                            restaurant.getAddress(),
                            restaurant.getRestaurantDescription()
                    );
                }).orElseThrow(
                        () -> new ResourceNotFoundException("Restaurant not found with id: " + id));
    }
}
