package com.hanghae99.goodssite.entity;

import com.hanghae99.goodssite.dto.CartRequestDto;
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
    @JoinColumn(name = "product_id")
    private Product product;

    public Cart(CartRequestDto requestDto) {
        this.count = requestDto.getCount();
    }

    public void setCountPlus(int count) {
        this.count = count;
    }

//    @ManyToOne
//    @JoinColumn(name = "user_id")
//    private User user;
}
