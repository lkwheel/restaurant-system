package com.codiecode.foodcatalog.repo;

import com.codiecode.foodcatalog.entity.FoodItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FoodItemRepo extends JpaRepository<FoodItem, Long> {

    List<FoodItem> findByRestaurantId(Long restaurantId);
}
