import { Component, OnInit } from '@angular/core';
import { Order } from '../model/order.model';
import { Product } from '../model/product.model';
import { User } from '../model/user.model';
import { CartService } from '../_services/cart.service';
import { TokenStorageService } from '../_services/token-storage.service';
import { OrderService } from '../_services/order.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-cart',
  templateUrl: './cart.component.html',
  styleUrls: ['./cart.component.css']
})
export class CartComponent implements OnInit {

  cartProducts:{
    product: Product,
    quantity:number
  }[];
  user:User;
  cartTotal:number;
  isLoggedIn:boolean = false;

  constructor(private cartService: CartService, private tokenService: TokenStorageService,
    private orderService: OrderService, private router: Router) { 
    this.isLoggedIn = !!this.tokenService.getToken();
    if (this.isLoggedIn) {
      this.user = this.tokenService.getUser();
    }
  }

  ngOnInit(): void {
    this.loadCart();
  }

  loadCart(){
    this.cartService.getCartProducts(this.user.userId).subscribe(
      data => {
        if(data.code == 200){
          this.cartProducts = data.data;
          this.calculateCartTotal();
          console.log(this.cartProducts);
        }else{
          console.log("API code not 200");
        }
      },
      err => {
        console.log(err);
      }
    );
  }

  calculateCartTotal(){
    let total = 0;
    this.cartProducts.map(cartProduct => {
      total += cartProduct.product.price * cartProduct.quantity;
    });
    this.cartTotal = total;
  }

  placeOrder(){
    let productItems:[{productId:number,quantity:number}] = [{"productId":0,"quantity":0}];
    this.cartProducts.forEach(cartProduct => {
      productItems.push({"productId" : cartProduct.product.productId,"quantity":cartProduct.quantity});
    });
    
    let orderDetails:Order = {
      "userId": this.user.userId,
      "productItems": productItems,
      "shippingAddress": "Test 123",
      "orderTotal": this.cartTotal,
    };
    this.orderService.createOrder(orderDetails).subscribe(
      data => {
        console.log(data);
        if(data.code == 200){
          this.emptyCart(this.user.userId);
          this.router.navigate(["/orders"])
        }
      }
    )
  }

  emptyCart(userId:number){
    this.cartService.emptyCart(userId).subscribe(
      data => {
        console.log(data);
      },
      err => {
        console.log(err);
      }
    )
  }

}
