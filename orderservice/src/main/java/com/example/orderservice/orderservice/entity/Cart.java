package com.example.orderservice.orderservice.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;

@Entity
@Table(name = "cart")
@Data
@AllArgsConstructor
@NoArgsConstructor
@IdClass(CartCompositeKey.class)
public class Cart {

    @Id
    @Column(name = "ProductId")
    private Integer productId;

    @Id
    @Column(name = "UserId")
    private Integer userId;

    @Column(name = "Quantity", nullable = false)
    private Integer quantity;

    @Column(name = "Created", nullable = false)
    private Date created;

    @Column(name = "Modified", nullable = false)
    private Date modified;

}

