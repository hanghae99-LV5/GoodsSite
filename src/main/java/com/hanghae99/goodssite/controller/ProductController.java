package com.hanghae99.goodssite.controller;

import com.hanghae99.goodssite.dto.product.ProductRequestDto;
import com.hanghae99.goodssite.dto.product.ProductResponseDto;
import com.hanghae99.goodssite.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;



    @PostMapping("/products")
    public ProductResponseDto createProduct(@RequestBody ProductRequestDto requestDto) {
        return productService.createProduct(requestDto);
    }
    @GetMapping("/products/{id}")
    public ProductResponseDto findProductById(@PathVariable Long id ) {
        return productService.findProductById(id);
    }

    @GetMapping("/products")
    public Page<ProductResponseDto> getProducts(
            @RequestParam("page") int page,
            @RequestParam("size") int size,
            @RequestParam("sortBy") String sortBy,
            @RequestParam("sortOrder") String sortOrder) {
        return productService.getProducts(page-1, size, sortBy, sortOrder);
    }
}
