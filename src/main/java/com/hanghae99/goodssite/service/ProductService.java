package com.hanghae99.goodssite.service;

import com.hanghae99.goodssite.dto.product.ProductRequestDto;
import com.hanghae99.goodssite.dto.product.ProductResponseDto;
import com.hanghae99.goodssite.entity.Product;
import com.hanghae99.goodssite.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public ProductResponseDto createProduct(ProductRequestDto requestDto) {
        Product product = productRepository.save(new Product(requestDto));
        return new ProductResponseDto(product);



    }
    public ProductResponseDto findProductById(Long id) {
        Product product = findProduct(id);
        return new ProductResponseDto(product);


    }
    private Product findProduct(Long id) {
        return productRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("해당하는 상품이 없습니다"));
    }

    public Page<ProductResponseDto> getProducts(int page, int size, String sortBy, String sortOrder) {
        Sort.Direction direction = "asc".equalsIgnoreCase(sortOrder) ? Sort.Direction.ASC : Sort.Direction.DESC;
        Sort sort = Sort.by(direction, sortBy);
        Pageable pageable = PageRequest.of(page, size, sort);
        Page<Product> productList;
        productList = productRepository.findAll(pageable);
        return productList.map(ProductResponseDto::new);
    }
}
