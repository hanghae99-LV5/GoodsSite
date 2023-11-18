package com.hanghae99.goodssite.controller;

import com.hanghae99.goodssite.dto.ProductRequestDto;
import com.hanghae99.goodssite.dto.ProductResponseDto;
import com.hanghae99.goodssite.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;
    @PostMapping("/products")
    public ProductResponseDto createProduct(@RequestBody ProductRequestDto requestDto) {
        return productService.createProduct(requestDto);
    }
}
