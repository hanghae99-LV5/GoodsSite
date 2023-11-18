package com.hanghae99.goodssite.dto.product;

import com.hanghae99.goodssite.entity.Product;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ProductRequestDto {
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

}
