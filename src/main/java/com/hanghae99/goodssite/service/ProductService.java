package com.hanghae99.goodssite.service;

import com.hanghae99.goodssite.dto.product.ProductRequestDto;
import com.hanghae99.goodssite.dto.product.ProductResponseDto;
import com.hanghae99.goodssite.entity.Product;
import com.hanghae99.goodssite.repository.ProductRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    public ResponseEntity createProduct(ProductRequestDto productRequestdto) {
        Product product = new Product(productRequestdto);
        productRepository.save(product);
        return ResponseEntity.status(HttpStatus.CREATED).body("상품이 등록되었습니다.");
    }

    public Page<ProductResponseDto> getProductPage(String sortBy, boolean isAsc, int size, int page) {
        Sort.Direction direction = isAsc ? Sort.Direction.ASC : Sort.Direction.DESC;
        Sort sort = Sort.by(direction,sortBy);

        Pageable pageable = PageRequest.of(page,size,sort);
        Page<Product> productList;
        productList = productRepository.findAll(pageable);
        return productList.map(ProductResponseDto::new);
    }

    public ProductResponseDto getProducts(Long id) {
        Product product = productRepository.findById(id).orElseThrow(()->
                        new EntityNotFoundException("존재하지않는 상품 번호입니다.")
                );
        return new ProductResponseDto(product);
    }
}
