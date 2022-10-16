import { Component, OnInit } from '@angular/core';
import { Order, OrderPlaced } from '../model/order.model';
import { User } from '../model/user.model';
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
  userOrders:OrderPlaced[];

  constructor(private orderService: OrderService, private tokenService: TokenStorageService) { }

  ngOnInit(): void {
    this.isLoggedIn = !!this.tokenService.getToken();
    if(this.isLoggedIn){
      this.user = this.tokenService.getUser();
    }
    this.getAllOrders(this.user.userId);
  }

  getAllOrders(userId:number):any{
    this.orderService.getAllOrders(userId).subscribe(
      data => {
        this.userOrders = data;
        console.log(this.userOrders);
      },
      err => {
        console.log(err);
      }
    )
  }

}
