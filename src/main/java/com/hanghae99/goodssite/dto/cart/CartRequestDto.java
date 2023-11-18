package com.hanghae99.goodssite.dto.cart;

import com.hanghae99.goodssite.entity.Product;
import lombok.Getter;

@Getter
public class CartRequestDto {
    private int count;
    private Long productId;
}
