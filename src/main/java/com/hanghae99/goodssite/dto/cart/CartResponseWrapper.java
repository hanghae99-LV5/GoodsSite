package com.hanghae99.goodssite.dto.cart;

import lombok.Getter;

import java.util.List;

@Getter
public class CartResponseWrapper {
    private List<CartResponseDto> cartList;
    private int cartTotalPrice;

    public CartResponseWrapper(List<CartResponseDto> cartList, int cartTotalPrice) {
        this.cartList = cartList;
        this.cartTotalPrice = cartTotalPrice;
    }
}
