package com.hanghae99.goodssite.dto;

import com.hanghae99.goodssite.entity.Product;
import lombok.Getter;

@Getter
public class CartRequestDto {
    private int count;
    private Long productId;
}
