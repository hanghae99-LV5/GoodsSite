package com.hanghae99.goodssite.service;

import com.hanghae99.goodssite.dto.ProductRequestDto;
import com.hanghae99.goodssite.dto.ProductResponseDto;
import com.hanghae99.goodssite.entity.Product;
import com.hanghae99.goodssite.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public ProductResponseDto createProduct(ProductRequestDto requestDto) {
        Product product = productRepository.save(new Product(requestDto));
        return new ProductResponseDto(product);



    }
}
