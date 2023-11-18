package com.hanghae99.goodssite.entity;

import com.hanghae99.goodssite.dto.cart.CartRequestDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "carts")
@NoArgsConstructor
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int count;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;


    public Cart(CartRequestDto requestDto, User user, Product product) {
        this.count = requestDto.getCount();
        this.user = user;
        this.product = product;
    }


    public void countUpdate(int count) {
        this.count = count;
    }
}
