package com.hanghae99.goodssite.repository;

import com.hanghae99.goodssite.entity.Cart;
import com.hanghae99.goodssite.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CartRepository extends JpaRepository<Cart, Long> {

    Optional<Cart> findByProduct(Product product);

//    Optional<Cart> findByProductId(Product product);
}
