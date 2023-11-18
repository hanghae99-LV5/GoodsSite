package com.hanghae99.goodssite.dto.cart;

import com.hanghae99.goodssite.dto.product.ProductResponseDto;
import com.hanghae99.goodssite.entity.Cart;
import com.hanghae99.goodssite.entity.Product;
import com.hanghae99.goodssite.entity.User;
import lombok.Getter;

@Getter
public class CartResponseDto {
    private Long id;
    private ProductResponseDto product;
    private int cartItemCount;
    private int totalPrice;

    public CartResponseDto(Cart cart) {
        this.id = cart.getId();;
        this.product = new ProductResponseDto(cart.getProduct());
        this.cartItemCount = cart.getCount();
        this.totalPrice = cart.getProduct().getPrice() * cart.getCount();
    }
}
