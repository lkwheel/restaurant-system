import { HttpClient } from '@angular/common/http';
import { Injectable, inject, signal } from '@angular/core';
import { catchError, finalize, throwError } from 'rxjs';
import { environment } from '../../../environments/environment';
import { Restaurant } from '../../shared/model/restaurant';

@Injectable({ providedIn: 'root' })
export class RestaurantService {
  private http = inject(HttpClient);
  private readonly apiUrl = `${environment.restaurantApi}/restaurant`;

  // Internal signals (private so components can't modify them directly)
  private _restaurants = signal<Restaurant[]>([]);
  private _isFetching = signal(false);
  private _error = signal('');

  // Public read-only signals
  restaurants = this._restaurants.asReadonly();
  isFetching = this._isFetching.asReadonly();
  error = this._error.asReadonly();

  loadAllRestaurants() {
    this._isFetching.set(true);
    this._error.set('');

    return this.http.get<Restaurant[]>(`${this.apiUrl}/fetchAllRestaurants`)
      .pipe(
        catchError(err => {
          this._error.set(err.message || 'Failed to fetch restaurants');
          return throwError(() => err);
        }),
        finalize(() => this._isFetching.set(false))
      )
      .subscribe(data => this._restaurants.set(data));
  }
}