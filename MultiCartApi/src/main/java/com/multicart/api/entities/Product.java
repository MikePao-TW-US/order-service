package com.multicart.api.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "products")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {

    @Id
    @Column(name = "ProductId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer productId;

    @Column(name = "ProductName", nullable = false)
    private String productName;

    @Column(name = "ProductDescription", nullable = false)
    private String productDescription;

    @Column(name = "Price", nullable = false)
    private Integer price;

    @Column(name = "Image", nullable = false)
    private String image;

    @Column(name = "Created", columnDefinition = "datetime default CURRENT_TIMESTAMP")
    private Date created;

    @Column(name = "Modified", columnDefinition = "datetime default CURRENT_TIMESTAMP")
    private Date modified;

    @Column(name = "Deleted", columnDefinition = "tinyint default 0")
    private Boolean deleted;
}
