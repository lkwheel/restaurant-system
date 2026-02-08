import { HttpClient } from '@angular/common/http';
import { inject, Injectable, signal } from '@angular/core';
import { environment } from '../../../environments/environment';
import { catchError, finalize, throwError } from 'rxjs';
import { FoodCatalogPage } from '../../shared/model/foodCatalogPage';

@Injectable({ providedIn: 'root' })
export class FoodCatalogService {
  private http = inject(HttpClient);
  private readonly apiUrl = `${environment.foodCatalogApi}/api/v1/foodCatalog`;

  private _foodCatalogPage = signal<FoodCatalogPage | undefined>(undefined);
  private _isFetching = signal(false);
  private _error = signal('');

  foodCatalogPage = this._foodCatalogPage.asReadonly();
  isFetching = this._isFetching.asReadonly();
  error = this._error.asReadonly();

  getFoodItemsByRestaurant(id: number) {
    this._isFetching.set(true);
    this._error.set('');

    this.http
      .get<FoodCatalogPage>(
        `${this.apiUrl}/findRestaurantAndFoodItemsById/${id}`,
      )
      .pipe(
        catchError((err) => {
          this._error.set(err.message || 'Failed to fetch catalog');
          return throwError(() => err);
        }),
        finalize(() => this._isFetching.set(false)),
      )
      .subscribe((data) => this._foodCatalogPage.set(data));
  }

  // Immutable update logic
  updateQuantity(foodId: number, delta: number) {
    this._foodCatalogPage.update((state) => {
      if (!state) return state;

      const updatedList = state.foodItemList.map((item) =>
        item.id === foodId
          ? { ...item, quantity: Math.max(0, (item.quantity || 0) + delta) }
          : item,
      );

      return { ...state, foodItemList: updatedList };
    });
  }
}
