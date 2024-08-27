import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';

const CART_API = 'http://35.86.195.130:8080/cart';
const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable({
  providedIn: 'root'
})
export class CartService {

  constructor(private http: HttpClient) { }

  addProductToCart(productId:number,userId:number): Observable<any>{
      return this.http.post(CART_API,{
        productId,
        userId
      }, httpOptions);
  }

  getCartProducts(userId:number): Observable<any>{
    return this.http.get(CART_API+"/"+userId);
  }

  updateCartProduct(productId:number,userId:number,quantity:number):Observable<any>{
    return this.http.put(CART_API,{
      productId,
      userId,
      quantity
    },httpOptions);
  }

  deleteProductFromCart(productId:number,userId:number):Observable<any>{
    return this.http.delete(CART_API+"/"+userId+"/"+productId);
  }

  emptyCart(userId:number):Observable<any>{
    return this.http.post(CART_API + "/empty",{
      userId
    },httpOptions);
  }
}
