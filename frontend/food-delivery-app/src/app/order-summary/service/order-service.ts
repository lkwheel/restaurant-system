import { HttpClient, HttpHeaders } from '@angular/common/http';
import { inject, Injectable, signal } from '@angular/core';
import { environment } from '../../../environments/environment';
import { catchError, finalize, Observable, throwError } from 'rxjs';

@Injectable({ providedIn: 'root' })
export class OrderService {
  private http = inject(HttpClient);
  private readonly apiUrl = `${environment.orderApi}/api/v1/order`;

  httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'text/plain',
      'Access-Control-Allow-Origin': environment.orderApi,
    }),
  };

  private _isFetching = signal(false);
  private _error = signal('');

  isFetching = this._isFetching.asReadonly();
  error = this._error.asReadonly();

  // Use a signal to hold the draft order
  private _currentOrder = signal<any | null>(null);
  currentOrder = this._currentOrder.asReadonly();

  // Methods to set and clear the order
  setOrder(order: any) {
    this._currentOrder.set(order);
  }

  saveOrder(data: any): Observable<any> {
    this._isFetching.set(true);
    this._error.set('');

    return this.http.post<any>(this.apiUrl + '/saveOrder', data).pipe(
      // This runs on both success AND error
      finalize(() => this._isFetching.set(false)),
      catchError((err) => {
        this._error.set(err.message || 'Failed to save order');
        return throwError(() => err);
      }),
    );
  }
}
