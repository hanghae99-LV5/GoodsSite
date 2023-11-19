package com.hanghae99.goodssite.dto.cart;

import com.hanghae99.goodssite.entity.Cart;
import lombok.Getter;

import java.util.List;

@Getter
public class CartResponseWrapper {
    private List<CartResponseDto> cartList;
    private int totalPrice;


    public CartResponseWrapper(List<CartResponseDto> cartList, int totalCartPrice) {
        this.cartList = cartList;
        this.totalPrice = totalCartPrice;
    }
}
