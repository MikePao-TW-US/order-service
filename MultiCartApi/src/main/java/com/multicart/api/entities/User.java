package com.multicart.api.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@Table(name = "users")
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "UserId",nullable = false,unique = true)
    private Integer userId;

    @Column(name = "Name",nullable = false)
    private String name;

    @Column(name = "Email", nullable = false, unique = true)
    private String email;

    @Column(name = "Password", nullable = false)
    private String password;

    @Column(name = "Created", columnDefinition = "datetime default CURRENT_TIMESTAMP")
    private Date created;

    @Column(name = "Modified", columnDefinition = "datetime default CURRENT_TIMESTAMP")
    private Date modified;
}
