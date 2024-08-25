import { Component, Input, Output, EventEmitter, OnInit } from '@angular/core';
import { Order, OrderPlaced  } from '../model/order.model';
import { OrderService } from '../_services/order.service';
import { TokenStorageService } from '../_services/token-storage.service';
import { Observable } from 'rxjs';

@Component({
  selector: 'app-order-item',
  templateUrl: './order-item.component.html',
  styleUrls: ['./order-item.component.css']
})
export class OrderItemComponent implements OnInit {

  @Input() order:OrderPlaced;
  @Output() orderDeleted = new EventEmitter<number>();
  
  constructor(private orderService: OrderService, private tokenService: TokenStorageService) { }

  ngOnInit(): void {
  }

  
  deleteOrders(orderId: number): void {
    this.orderService.deleteOrder(orderId).subscribe({
      next: () => {
        this.orderDeleted.emit(orderId); // Emit the event after the order is deleted
      },
      error: (err) => {
        console.error("Failed to delete order", err);
      },
      complete: () => {
        console.log("Delete operation completed");
      }
    });
  }
}
