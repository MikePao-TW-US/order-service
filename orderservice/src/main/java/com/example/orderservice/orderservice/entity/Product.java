package com.example.orderservice.orderservice.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.Date;

@Entity
@Table(name = "products")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ProductId")
    private Integer productId;

    @NotBlank
    @Column(name = "ProductName", nullable = false)
    private String productName;

    @NotBlank
    @Column(name = "ProductDescription", nullable = false)
    private String productDescription;

    @NotNull
    @Min(value = 0)
    @Column(name = "Price", nullable = false)
    private Integer price;

    @NotBlank
    @Column(name = "Image", nullable = false)
    private String image;

    @Column(name = "Created", nullable = false)
    private Date created;

    @Column(name = "Modified", nullable = false)
    private Date modified;

    @Column(name = "Deleted")
    private Boolean deleted;
}
