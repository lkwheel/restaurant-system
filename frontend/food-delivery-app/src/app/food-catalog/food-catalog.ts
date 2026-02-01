import { Component, computed, inject, input, OnInit } from '@angular/core';
import { FoodCatalogService as FoodCatalogService } from './service/food-catalog-service';
import { Router } from '@angular/router';
import { OrderService } from '../order-summary/service/order-service';

@Component({
  selector: 'app-food-catalog',
  imports: [],
  templateUrl: './food-catalog.html',
  styleUrl: './food-catalog.css',
  standalone: true,
})
export class FoodCatalog implements OnInit {
  foodCatalogService = inject(FoodCatalogService);
  orderService = inject(OrderService);
  private router = inject(Router);

  // Read-only views of the service state
  foodItemsByRestaurant = this.foodCatalogService.foodCatalogPage;
  isFetching = this.foodCatalogService.isFetching;
  errorMessage = this.foodCatalogService.error;

  // Required input from the route
  restaurantId = input.required<number>();

  // Computed signal that acts as our "Cart"
  // It automatically updates whenever foodItemsByRestaurant changes!
  foodItemCart = computed(() => {
    const page = this.foodItemsByRestaurant();
    return page ? page.foodItemList.filter((item) => item.quantity > 0) : [];
  });

  ngOnInit() {
    // Fetch if we don't have data, or if the ID changed
    this.foodCatalogService.getFoodItemsByRestaurant(this.restaurantId());
  }

  increment(foodId: number) {
    this.foodCatalogService.updateQuantity(foodId, 1);
  }

  decrement(foodId: number) {
    this.foodCatalogService.updateQuantity(foodId, -1);
  }

  onCheckOut() {
    const orderSummary = {
      foodItemDTOList: this.foodItemCart(),
      restaurant: this.foodItemsByRestaurant()?.restaurant,
      userId: 1, // Hardcoded for now, or get from an AuthService
    };

    // 1. Save to shared service
    this.orderService.setOrder(orderSummary);

    this.router.navigate(['/orderSummary']);
  }
}
