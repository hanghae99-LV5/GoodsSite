package com.hanghae99.goodssite.controller;

import com.hanghae99.goodssite.dto.product.ProductRequestDto;
import com.hanghae99.goodssite.dto.product.ProductResponseDto;
import com.hanghae99.goodssite.security.UserDetailsImpl;
import com.hanghae99.goodssite.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping("/products")
    public ResponseEntity createProduct(@RequestBody ProductRequestDto productRequestdto){
        return productService.createProduct(productRequestdto);
    }

    @GetMapping("/products")
    public Page<ProductResponseDto> getProductPage(@RequestParam("sort") String sort,
                                                   @RequestParam("isAsc") boolean isAsc,
                                                   @RequestParam("size") int size,
                                                   @RequestParam("page") int page){
        return productService.getProductPage(sort,isAsc,size,page);
    }

    @GetMapping("/prducts/{id}")
    public ResponseEntity<ProductResponseDto> getProducts(@PathVariable Long id){
        return new ResponseEntity<>(productService.getProducts(id) , HttpStatus.OK);
    }
}
