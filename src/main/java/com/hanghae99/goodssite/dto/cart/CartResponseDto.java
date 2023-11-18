package com.hanghae99.goodssite.dto.cart;

import com.hanghae99.goodssite.dto.product.ProductResponseDto;
import com.hanghae99.goodssite.entity.Cart;
import com.hanghae99.goodssite.entity.Product;
import com.hanghae99.goodssite.entity.User;
import lombok.Getter;

@Getter
public class CartResponseDto {
    private Long id;
    private User user;
    private ProductResponseDto product;

    public CartResponseDto(Cart cart, Product product) {
        this.id = cart.getId();;
//        this.product = product.getProduct();
    }
}
