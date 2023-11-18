package com.hanghae99.goodssite.entity;

import com.hanghae99.goodssite.dto.product.ProductRequestDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "goods")
@NoArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private Integer price;
    private Integer count;
    private String intro;
    private String category;

    public Product(ProductRequestDto requestDto) {
        this.name = requestDto.getName();
        this.price = requestDto.getPrice();
        this.count = requestDto.getCount();
        this.intro = requestDto.getIntro();
        this.category = requestDto.getCategory();
    }
}
