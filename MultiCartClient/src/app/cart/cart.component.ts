import { Component, OnInit } from '@angular/core';
import { Product } from '../model/product.model';
import { User } from '../model/user.model';
import { CartService } from '../_services/cart.service';
import { TokenStorageService } from '../_services/token-storage.service';

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

  constructor(private cartService: CartService, private tokenService: TokenStorageService) { 
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

}
