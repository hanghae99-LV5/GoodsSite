package com.hanghae99.goodssite.repository;

import com.hanghae99.goodssite.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
