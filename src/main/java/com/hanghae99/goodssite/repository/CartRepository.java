package com.hanghae99.goodssite.repository;

import com.hanghae99.goodssite.entity.Cart;
import com.hanghae99.goodssite.entity.Product;
import com.hanghae99.goodssite.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CartRepository extends JpaRepository<Cart, Long> {

    Optional<Cart> findByProduct(Product product);

    List<Cart> findByUser(User userId);

//    Optional<Cart> findByProductId(Product product);
}
