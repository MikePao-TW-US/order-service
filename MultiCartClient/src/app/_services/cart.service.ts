import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';

const CART_API = 'http://localhost:9090/cart/';
const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable({
  providedIn: 'root'
})
export class CartService {

  constructor(private http: HttpClient) { }

  addProductToCart(productId:number,userId:number): Observable<any>{
      return this.http.post(CART_API + 'add',{
        productId,
        userId
      }, httpOptions);
  }

  getCartProducts(userId:number): Observable<any>{
    return this.http.post(CART_API + "getProducts",{
      userId
    }, httpOptions);
  }

  updateCartProduct(productId:number,userId:number,quantity:number):Observable<any>{
    return this.http.post(CART_API + "update",{
      productId,
      userId,
      quantity
    },httpOptions);
  }

  deleteProductFromCart(productId:number,userId:number):Observable<any>{
    return this.http.post(CART_API + "delete",{
      productId,
      userId
    }, httpOptions);
  }

  emptyCart(userId:number):Observable<any>{
    return this.http.post(CART_API + "empty",{
      userId
    },httpOptions);
  }
}
