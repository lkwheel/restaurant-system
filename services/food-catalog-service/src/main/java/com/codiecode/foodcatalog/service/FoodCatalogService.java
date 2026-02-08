package com.codiecode.foodcatalog.service;

import com.codiecode.common.dto.FoodItemDTO;
import com.codiecode.common.dto.RestaurantDTO;
import com.codiecode.foodcatalog.dto.FoodCatalogPage;
import com.codiecode.foodcatalog.entity.FoodItem;
import com.codiecode.foodcatalog.repo.FoodItemRepo;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class FoodCatalogService {

    private static final String RESTAURANT_SERVICE_URL = "http://RESTAURANT-SERVICE";
    private static final String GET_RESTAURANT_ENDPOINT = "api/v1/restaurant/fetchById";

    private final FoodItemRepo foodItemRepo;
    private final RestTemplate restTemplate;

    public FoodCatalogService(FoodItemRepo foodItemRepo, RestTemplate restTemplate) {
        this.foodItemRepo = foodItemRepo;
        this.restTemplate = restTemplate;
    }


    public FoodItemDTO addFoodItem(FoodItemDTO foodItem) {
        FoodItem foodItemToAdd = new FoodItem(
                foodItem.getItemName(),
                foodItem.getItemDescription(),
                foodItem.isVegetarian(),
                foodItem.getPrice(),
                foodItem.getRestaurantId(),
                foodItem.getQuantity()
        );
        FoodItem foodItemSaved = foodItemRepo.save(foodItemToAdd);
        return new FoodItemDTO(foodItemSaved.getId(),
                foodItemSaved.getItemName(),
                foodItemSaved.getItemDescription(),
                foodItemSaved.isVegetarian(),
                foodItemSaved.getPrice(),
                foodItemSaved.getRestaurantId(),
                foodItemSaved.getQuantity()
        );
    }

    public FoodCatalogPage fetchFoodCatalogPageDetails(Long restaurantId) {
        List<FoodItem> foodItemList = fetchFoodItemList(restaurantId);
        RestaurantDTO restaurantDTO = fetchRestaurantFromRestaurantMS(restaurantId);
        return createFoodCatalogPage(foodItemList, restaurantDTO);
    }

    private List<FoodItem> fetchFoodItemList(Long restaurantId) {
        return foodItemRepo.findByRestaurantId(restaurantId);
    }

    private RestaurantDTO fetchRestaurantFromRestaurantMS(Long restaurantId) {
        String url = String.format("%s/%s/%d", RESTAURANT_SERVICE_URL, GET_RESTAURANT_ENDPOINT, restaurantId);
        return this.restTemplate.getForObject(url, RestaurantDTO.class);
    }

    private FoodCatalogPage createFoodCatalogPage(List<FoodItem> foodItemList, RestaurantDTO restaurantDTO) {
        return new FoodCatalogPage(foodItemList, restaurantDTO);
    }

    public List<FoodItemDTO> fetchAll() {
        List<FoodItem> foodItemList = foodItemRepo.findAll();
        return foodItemList.stream()
                .map(foodItem -> new FoodItemDTO(
                        foodItem.getId(),
                        foodItem.getItemName(),
                        foodItem.getItemDescription(),
                        foodItem.isVegetarian(),
                        foodItem.getPrice(),
                        foodItem.getRestaurantId(),
                        foodItem.getQuantity()
                ))
                .toList();
    }
}
