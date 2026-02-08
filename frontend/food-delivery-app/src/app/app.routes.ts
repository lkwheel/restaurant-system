import { Router, Routes } from '@angular/router';
import { RestaurantListing } from './restaurant-listing/restaurant-listing';
import { FoodCatalog } from './food-catalog/food-catalog';
import { OrderSummary } from './order-summary/order-summary';
import { inject } from '@angular/core';
import { OrderService } from './order-summary/service/order-service';

export const routes: Routes = [
  { path: '', component: RestaurantListing },
  { path: 'restaurants', component: RestaurantListing },
  { path: 'food-catalog/:restaurantId', component: FoodCatalog },
  { 
    path: 'orderSummary', 
    component: OrderSummary,
    // Optional: Add a guard to ensure an order exists in the service
    canActivate: [() => inject(OrderService).currentOrder() ? true : inject(Router).parseUrl('/')]
  },
];
