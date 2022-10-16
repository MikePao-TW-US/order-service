import { Component, Input, OnInit } from '@angular/core';
import { Product } from '../model/product.model';
import { User } from '../model/user.model';
import { TokenStorageService } from '../_services/token-storage.service';
import { Router } from '@angular/router';
import { CartService } from '../_services/cart.service';

@Component({
  selector: 'app-product',
  templateUrl: './product.component.html',
  styleUrls: ['./product.component.css']
})
export class ProductComponent implements OnInit {

  @Input() product: Product;

  isLoggedIn = false;
  user:User;

  constructor(private tokenService: TokenStorageService, private router: Router,
    private cartService: CartService) { }

  ngOnInit(): void {
    this.isLoggedIn = !!this.tokenService.getToken();

    if (this.isLoggedIn) {
      this.user = this.tokenService.getUser();
    }
  }

  addToCart(productId:number):any{
    if(this.isLoggedIn){
      this.cartService.addProductToCart(productId,this.user.userId).subscribe(
        data => {
          if(data.code == 200){
            alert(data.message);
          }
          console.log(data);
        },
        err => {
          console.log(err);
        }
      )
    }else{
      this.router.navigate(["/login"]).then(() => {
        window.location.reload();
      })
    }
  }

}
