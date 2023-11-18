package com.hanghae99.goodssite.service;

import com.hanghae99.goodssite.dto.cart.CartRequestDto;
import com.hanghae99.goodssite.entity.Cart;
import com.hanghae99.goodssite.entity.Product;
import com.hanghae99.goodssite.entity.User;
import com.hanghae99.goodssite.repository.CartRepository;
import com.hanghae99.goodssite.repository.ProductRepository;
import com.hanghae99.goodssite.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CartService {

    private final CartRepository cartRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;


    @Transactional
    public ResponseEntity addCart(CartRequestDto requestDto, Long userId) {
        // 상품 찾기
        Product product = productRepository.findById(requestDto.getProductId())
                .orElseThrow(() -> new NullPointerException("상품이 존재하지 않습니다."));

        // 유저 찾기
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new NullPointerException("유저가 존재하지 않습니다."));

        // 이미 존재하는 상품
        Optional<Cart> duplicatedCart = cartRepository.findByProduct(product);
        duplicatedCart.ifPresent(item -> {
            throw new IllegalArgumentException("이미 상품이 존재합니다.");
        });

        // 상품이 카트에 없으면 새로운 카트 아이템을 추가
        Cart cart = new Cart(requestDto, user, product);
        cartRepository.save(cart);
        return ResponseEntity.status(HttpStatus.OK).body("카트에 아이템이 추가되었습니다.");
    }
}
