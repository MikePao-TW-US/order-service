import { Component, OnInit } from '@angular/core';
import { OrderPlaced } from '../model/order.model';
import { User } from '../model/user.model';
import { Observable } from 'rxjs';
import { OrderService } from '../_services/order.service';
import { TokenStorageService } from '../_services/token-storage.service';

@Component({
  selector: 'app-orders',
  templateUrl: './orders.component.html',
  styleUrls: ['./orders.component.css']
})
export class OrdersComponent implements OnInit {

  isLoggedIn:boolean = false;
  user:User;
  userOrders$: Observable<OrderPlaced[]>;  // Observable of orders

  constructor(private orderService: OrderService, private tokenService: TokenStorageService) { }

  ngOnInit(): void {
    this.isLoggedIn = !!this.tokenService.getToken();
    if(this.isLoggedIn){
      this.user = this.tokenService.getUser();
    }
    this.userOrders$ = this.getAllOrders(this.user.userId);
  }

  getAllOrders(userId: number): Observable<OrderPlaced[]> {
    return this.orderService.getAllOrders(userId);
  }

  deleteOrders(orderId: number) {
    this.orderService.deleteOrder(orderId).subscribe(() => {
      this.userOrders$ = this.getAllOrders(this.user.userId);
    });
  }

  onOrderDeleted(): void {
    this.userOrders$ = this.getAllOrders(this.user.userId); // Refresh the orders list
  }

}
