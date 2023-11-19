package com.hanghae99.goodssite.entity;

import com.hanghae99.goodssite.dto.product.ProductRequestDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CollectionIdJdbcTypeCode;

@Getter
@Setter
@Entity
@Table(name="products")
@NoArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private int price;

    @Column(nullable = false)
    private int count;

    @Column(nullable = false)
    private String intro;

    @Column(nullable = false)
    private String category;


    public Product(ProductRequestDto productRequestdto) {
        this.name = productRequestdto.getName();
        this.price = productRequestdto.getPrice();
        this.count = productRequestdto.getCount();
        this.intro = productRequestdto.getIntro();
        this.category = productRequestdto.getCategory();
    }

}
