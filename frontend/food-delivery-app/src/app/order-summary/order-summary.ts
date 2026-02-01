import { Component, computed, inject, input, OnInit, signal } from '@angular/core';
import { Router } from '@angular/router';
import { OrderService } from './service/order-service';

@Component({
  selector: 'app-order-summary',
  imports: [],
  templateUrl: './order-summary.html',
  styleUrl: './order-summary.css',
  standalone: true,
})
export class OrderSummary {
  orderService = inject(OrderService);
  router = inject(Router);

  isFetching = this.orderService.isFetching;
  errorMessage = this.orderService.error;

  // Signal to control the Success Dialog
  showDialog = signal(false);

  order = this.orderService.currentOrder;

  total = computed(() => {
    // Ensure we use the correct field name from your DTO
    const items = this.order()?.foodItemDTOList || [];
    return items.reduce(
      (acc: number, curr: any) => acc + curr.quantity * curr.price,
      0,
    );
  });

  saveOrder() {
    const currentOrder = this.order();
    if (!currentOrder) return;

    this.orderService.saveOrder(currentOrder).subscribe({
      next: () => {
        this.showDialog.set(true); // Open our custom dialog instead of alert
      },
      error: (err) => console.error('Save failed', err),
    });
  }

  closeDialog() {
    this.showDialog.set(false);
    this.router.navigate(['/restaurants']);
  }
}
