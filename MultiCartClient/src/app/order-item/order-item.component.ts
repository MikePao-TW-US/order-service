import { Component, Input, OnInit } from '@angular/core';
import { Order, OrderPlaced } from '../model/order.model';

@Component({
  selector: 'app-order-item',
  templateUrl: './order-item.component.html',
  styleUrls: ['./order-item.component.css']
})
export class OrderItemComponent implements OnInit {

  @Input() order:OrderPlaced;
  
  constructor() { }

  ngOnInit(): void {
  }

}
