package com.example.orderservice.orderservice.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.Date;

@Entity
@Table(name = "orders")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "OrderId")
    private Integer orderId;

    @Column(name = "UserId", nullable = false)
    private Integer userId;

    @Column(name = "Total", nullable = false)
    private Integer total;

    @Column(name = "ShippingAddress", nullable = false)
    private String shippingAddress;

    @Column(name = "Status",nullable = false)
    private String status;

    @Column(name = "Created", nullable = false)
    private Date created;

    @Column(name = "Modified", nullable = false)
    private Date modified;
}

