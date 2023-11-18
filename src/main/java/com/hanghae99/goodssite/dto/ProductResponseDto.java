package com.hanghae99.goodssite.dto;

import com.hanghae99.goodssite.entity.Product;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ProductResponseDto {
    // 상품 id
    private Long id;
    // 상품명
    private String name;
    // 가격
    private Integer price;
    // 수량
    private Integer count;
    // 소개
    private String intro;
    // 카테고리
    private String category;
    public ProductResponseDto(Product product) {
        this.id = product.getId();
        this.name = product.getName();
        this.price = product.getPrice();
        this.count = product.getCount();
        this.intro = product.getIntro();
        this.category = product.getCategory();
    }
}
