package com.multicart.api.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
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

    @Column(name = "Created", columnDefinition = "datetime default CURRENT_TIMESTAMP", nullable = false)
    private Date created;

    @Column(name = "Modified", columnDefinition = "datetime default CURRENT_TIMESTAMP", nullable = false)
    private Date modified;

}
