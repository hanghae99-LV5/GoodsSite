package com.hanghae99.goodssite.service;

import com.hanghae99.goodssite.dto.cart.CartRequestDto;
import com.hanghae99.goodssite.dto.cart.CartResponseDto;
import com.hanghae99.goodssite.dto.cart.CartResponseWrapper;
import com.hanghae99.goodssite.entity.Cart;
import com.hanghae99.goodssite.entity.Product;
import com.hanghae99.goodssite.entity.User;
import com.hanghae99.goodssite.repository.CartRepository;
import com.hanghae99.goodssite.repository.ProductRepository;
import com.hanghae99.goodssite.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
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
        User user = findUser(userId);

        // 이미 존재하는 상품
        Optional<Cart> duplicatedCart = cartRepository.findByProduct(product);
        duplicatedCart.ifPresent(item -> {
            throw new IllegalArgumentException("이미 상품이 존재합니다.");
        });

        // 상품의 개수와 장바구니 개수 비교
        if (requestDto.getCount() > product.getCount()) {
            throw new IllegalArgumentException("상품의 최대 개수는 " + product.getCount() + " 입니다.");
        }

        // 상품이 카트에 없으면 새로운 카트 아이템을 추가
        Cart cart = new Cart(requestDto, user, product);
        cartRepository.save(cart);
        return ResponseEntity.status(HttpStatus.OK).body("카트에 아이템이 추가되었습니다.");
    }

    public CartResponseWrapper cartList(Long userId) {
        // 유저 찾기
        User user = findUser(userId);

        // 카트 조회
        List<Cart> cartList = cartRepository.findByUser(user);

        // 카트에 담긴 물건 총 금액
        int totalCartPrice = cartList.stream().mapToInt(cart -> cart.getProduct().getPrice() * cart.getCount()).sum();

        // 카트 조회 -> responseDto
        CartResponseWrapper responseWrapper = new CartResponseWrapper(
                cartList.stream().map(CartResponseDto::new).toList(),
                totalCartPrice
            );
        return responseWrapper;
    }

    @Transactional
    public ResponseEntity editCartItem(CartRequestDto cartRequestDto, Long cartId, Long userId) {
        // 유저 조회
        User user = findUser(userId);

        // 장바구니 아이템 조회
        Cart cart = findCartItem(cartId);

        // 유저와 장바구니 비교
        if (!Objects.equals(user.getId(), cart.getUser().getId())) {
            throw new IllegalArgumentException("장바구니의 주인이 아닙니다.");
        }

        // 상품의 개수와 장바구니 개수 비교
        if (cartRequestDto.getCount()  > cart.getProduct().getCount()) {
            throw new IllegalArgumentException("상품의 최대 개수는 " + cart.getProduct().getCount() + " 입니다.");
        }

        cart.countUpdate(cartRequestDto.getCount());
        return ResponseEntity.status(HttpStatus.OK).body("장바구니 수량을 수정하였습니다.");
    }

    public ResponseEntity deleteCartItem(Long cartId) {
        // 장바구니 아이템 조회
        Cart cart = findCartItem(cartId);

        // 삭제
        cartRepository.delete(cart);
        return ResponseEntity.status(HttpStatus.OK).body("장바구니 아이템을 삭제하였습니다.");
    }

    private User findUser(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("회원이 아니거나 유저를 찾을 수 없습니다."));
    }

    private Cart findCartItem(Long cartId) {
        return cartRepository.findById(cartId)
                .orElseThrow(() -> new EntityNotFoundException("카트가 담겨있는 아이템이 아닙니다."));
    }

}
