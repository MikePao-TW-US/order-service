import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Order } from '../model/order.model';

const ORDER_API = 'http://localhost:9090/order/';
const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable({
  providedIn: 'root'
})
export class OrderService {

  constructor(private http: HttpClient) { }

  createOrder(orderDetails: Order): Observable<any> {
    return this.http.post(ORDER_API + "create", {
      "userId": orderDetails.userId,
      "productItems": orderDetails.productItems,
      "shippingAddress": "Test 123",
      "orderTotal": orderDetails.orderTotal
    }, httpOptions);
  }

  getAllOrders(userId:number):Observable<any>{
    return this.http.post(ORDER_API + "get",{
      userId
    },httpOptions);
  }
}
