package com.elshamy.ecommerceapi.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.List;

@Entity
@Table(name = "cart")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;

    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL)
    private List<CartItem> items;
}