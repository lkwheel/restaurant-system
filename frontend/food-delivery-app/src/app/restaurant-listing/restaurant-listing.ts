import { Component, inject, OnInit } from '@angular/core';
import { RestaurantService } from './service/restaurant-service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-restaurant-listing',
  imports: [],
  templateUrl: './restaurant-listing.html',
  styleUrl: './restaurant-listing.css',
  standalone: true,
})
export class RestaurantListing implements OnInit {
  // Inject the service and just reference its signals
  restaurantService = inject(RestaurantService);
  private router = inject(Router);

  // Expose them to the template
  restaurantList = this.restaurantService.restaurants;
  isFetching = this.restaurantService.isFetching;
  errorMessage = this.restaurantService.error;

  ngOnInit() {
    // If the list is empty, fetch it.
    if (this.restaurantList().length === 0) {
      this.restaurantService.loadAllRestaurants();
    }
  }

  getRandomNumber(min: number, max: number): number {
    return Math.floor(Math.random() * (max - min + 1)) + min;
  }

  getRandomImage(): string {
    const imageCount = 8;
    const randomIndex = this.getRandomNumber(1, imageCount);
    return `restaurant-pics/${randomIndex}.jpg`;
  }

  onButtonClick(id: number) {
    this.router.navigate(['/food-catalog', id]);
  }
}
