import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';

const PRODUCT_API = 'http://localhost:9090/product/';
const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable({
  providedIn: 'root'
})
export class ProductService {

  constructor(private http: HttpClient) { }

  loadProducts(): Observable<any> {
    return this.http.get(PRODUCT_API + 'getall', httpOptions);
  }

}
