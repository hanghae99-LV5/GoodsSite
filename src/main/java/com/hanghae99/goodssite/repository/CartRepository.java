package com.hanghae99.goodssite.repository;

import com.hanghae99.goodssite.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CartRepository extends JpaRepository<Cart, Long> {
    Optional<Cart> findByProduct(Long id);

    List<Cart> findAllByUser(Long userId);

    Optional<Cart> findByIdAndUserId(Long id, Long userId);
}
