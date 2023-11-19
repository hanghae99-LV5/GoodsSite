package com.hanghae99.goodssite.dto.product;

import lombok.Getter;

@Getter
public class ProductRequestDto {
    private String name;
    private int price;
    private int count;
    private String intro;
    private String category;

}
