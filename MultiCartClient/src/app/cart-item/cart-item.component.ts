import { Component, Input, OnInit } from '@angular/core';
import { Product } from '../model/product.model';
import { User } from '../model/user.model';
import { CartService } from '../_services/cart.service';
import { TokenStorageService } from '../_services/token-storage.service';

@Component({
  selector: 'app-cart-item',
  templateUrl: './cart-item.component.html',
  styleUrls: ['./cart-item.component.css']
})
export class CartItemComponent implements OnInit {

  @Input() product:Product;
  @Input() quantity:number;

  isLoggedIn:boolean = false;
  user:User;

  constructor(private cartService: CartService, private tokenService: TokenStorageService) { }

  ngOnInit(): void {
    this.isLoggedIn = !!this.tokenService.getToken();
    if(this.isLoggedIn){
      this.user = this.tokenService.getUser();
    }
  }

  deleteProductFromCart(productId:number, userId:number):any{
    this.cartService.deleteProductFromCart(productId,userId).subscribe(
      data => {
        console.log(data);
        window.location.reload();
      },
      err => {
        console.log(err);
      }
    )
  }

  updateCartProduct(productId:number,userId:number,quantity:number):any{
    this.cartService.updateCartProduct(productId,userId,quantity).subscribe(
      data => {
        console.log(data);
        window.location.reload();
      },
      err => {
        console.log(err);
      }
    )
  }

  decreaseQuantity(productId:number, userId:number):any{
    if(this.quantity == 1){
      this.deleteProductFromCart(productId,userId);
    }else{
      this.updateCartProduct(productId,userId,this.quantity-1);
    }
  }

  increaseQuantity(productId:number, userId:number):any{
    this.updateCartProduct(productId,userId,this.quantity+1);
  }

}
