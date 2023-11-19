package com.hanghae99.goodssite.entity;

import com.hanghae99.goodssite.dto.cart.CartRequestDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="carts")
@NoArgsConstructor
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int count;

    @ManyToOne
    private Product product;

    @ManyToOne
    private User user;

    public Cart(CartRequestDto cartRequestDto, User user, Product product) {
        this.count = cartRequestDto.getCount();
        this.user = user;
        this.product = product;
    }
}
