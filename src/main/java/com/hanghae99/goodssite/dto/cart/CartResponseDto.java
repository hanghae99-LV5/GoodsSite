package com.hanghae99.goodssite.dto.cart;

import com.hanghae99.goodssite.dto.product.ProductRequestDto;
import com.hanghae99.goodssite.dto.product.ProductResponseDto;
import com.hanghae99.goodssite.entity.Cart;
import com.hanghae99.goodssite.entity.Product;
import lombok.Getter;

@Getter
public class CartResponseDto {
    private Long id;
    private int totalPrice;
    private int cartItemCount;
    private ProductResponseDto product;

    public CartResponseDto(Cart cart){
        this.id = cart.getId();
        this.cartItemCount = cart.getCount();
        this.totalPrice = cart.getProduct().getPrice() * cart.getCount();
        this.product = new ProductResponseDto(cart.getProduct());
    }

}
