package com.multicart.api.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
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

    @Column(name = "Status",columnDefinition = "ENUM('In Transit', 'Delivered', 'Cancelled') default 'In Transit'",nullable = false)
    private String status;

    @Column(name = "Created", columnDefinition = "datetime default CURRENT_TIMESTAMP", nullable = false)
    private Date created;

    @Column(name = "Modified", columnDefinition = "datetime default CURRENT_TIMESTAMP", nullable = false)
    private Date modified;
}
