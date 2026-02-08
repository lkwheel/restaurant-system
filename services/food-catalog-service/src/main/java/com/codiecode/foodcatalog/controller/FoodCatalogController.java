package com.codiecode.foodcatalog.controller;

import com.codiecode.common.dto.FoodItemDTO;
import com.codiecode.foodcatalog.dto.FoodCatalogPage;
import com.codiecode.foodcatalog.service.FoodCatalogService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/foodCatalog")
@CrossOrigin
public class FoodCatalogController {

    private final FoodCatalogService foodCatalogService;

    public FoodCatalogController(FoodCatalogService foodCatalogService) {
        this.foodCatalogService = foodCatalogService;
    }

    @GetMapping()
    public ResponseEntity<List<FoodItemDTO>> fetchAllFoodItems() {
        List<FoodItemDTO> foodItemDTOList = foodCatalogService.fetchAll();
        return new ResponseEntity<>(foodItemDTOList, HttpStatus.OK);
    }

    @PostMapping("/addFoodItem")
    public ResponseEntity<FoodItemDTO> addFoodItem(@RequestBody FoodItemDTO foodItem) {
        FoodItemDTO foodItemSaved = foodCatalogService.addFoodItem(foodItem);
        return new ResponseEntity<>(foodItemSaved, HttpStatus.CREATED);
    }

    @GetMapping("/findRestaurantAndFoodItemsById/{id}")
    public ResponseEntity<FoodCatalogPage> findRestaurantDetailsWithFoodMenu(@PathVariable Long id) {
        FoodCatalogPage foodCatalogPage = foodCatalogService.fetchFoodCatalogPageDetails(id);
        return new ResponseEntity<>(foodCatalogPage, HttpStatus.OK);
    }
}
