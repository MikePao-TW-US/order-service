package com.example.orderservice.orderservice.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.Date;

@Entity
@Table(name = "order_items")
@Data
@AllArgsConstructor
@NoArgsConstructor
@IdClass(OrderItemCompositeKey.class)
public class OrderItem {
    @Id
    @Column(name = "OrderId", nullable = false)
    private Integer orderId;

    @Id
    @Column(name = "ProductId", nullable = false)
    private Integer productId;

    @Column(name = "Quantity", nullable = false)
    private Integer quantity;

    @Column(name = "Created", nullable = false)
    private Date created;

    @Column(name = "Modified", nullable = false)
    private Date modified;
}
