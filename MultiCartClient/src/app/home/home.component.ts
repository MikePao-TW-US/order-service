import { Component, OnInit } from '@angular/core';
import { Product } from '../model/product.model';
import { ProductService } from '../_services/product.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  products: Product[] | undefined;

  constructor(private productService: ProductService) { 
    this.productService.loadProducts().subscribe(
      data => {
        this.products = data;
        console.log(this.products);
      },
      err => { }
    );
  }

  ngOnInit(): void {
  }

}
