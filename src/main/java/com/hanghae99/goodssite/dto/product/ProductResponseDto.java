package com.hanghae99.goodssite.dto.product;

import com.hanghae99.goodssite.entity.Product;
import lombok.Getter;

@Getter
public class ProductResponseDto {
    private String name;
    private int price;
    private int count;
    private String intro;
    private String category;

    public ProductResponseDto(Product product){
        this.name = product.getName();
        this.price = product.getPrice();
        this.count = product.getCount();
        this.intro = product.getIntro();
        this.category = product.getCategory();
    }
}
