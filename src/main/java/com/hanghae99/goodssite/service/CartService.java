package com.hanghae99.goodssite.service;

import com.hanghae99.goodssite.dto.cart.CartRequestDto;
import com.hanghae99.goodssite.dto.cart.CartResponseDto;
import com.hanghae99.goodssite.dto.cart.CartResponseWrapper;
import com.hanghae99.goodssite.dto.product.ProductResponseDto;
import com.hanghae99.goodssite.entity.Cart;
import com.hanghae99.goodssite.entity.Product;
import com.hanghae99.goodssite.entity.User;
import com.hanghae99.goodssite.repository.CartRepository;
import com.hanghae99.goodssite.repository.ProductRepository;
import com.hanghae99.goodssite.repository.UserRepository;
import com.hanghae99.goodssite.security.UserDetailsImpl;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CartService {
    private final CartRepository cartRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;

    public ResponseEntity addCart(CartRequestDto cartRequestDto, Long userId) {

        Product product = productRepository.findById(cartRequestDto.getProductId()).orElseThrow(() ->
                new EntityNotFoundException("존재하지않는 상품입니다.")
        );

        User user = userRepository.findById(userId).orElseThrow(() ->
                new EntityNotFoundException("존재하지않는 사용자입니다.")
        );

        Optional<Cart> alreadyCart = cartRepository.findByProduct(product.getId());
        if (alreadyCart.isPresent()) {
            throw new EntityExistsException("이미 존재하는 상품입니다.");
        }

        if(cartRequestDto.getCount() > product.getCount()){
            throw new IllegalArgumentException("상품의 최대 갯수는" + product.getCount() + "개 입니다.");
        }

        Cart cart = new Cart(cartRequestDto, user, product);

        cartRepository.save(cart);

        return ResponseEntity.status(HttpStatus.CREATED).body("장바구니에 상품이 추가되었습니다.");
    }


    public CartResponseWrapper getCart(Long userId) {
        List<Cart> cartList = cartRepository.findAllByUser(userId);

        int totalCartPrice = cartList.stream().mapToInt(cart -> cart.getProduct().getPrice() * cart.getCount()).sum();

        CartResponseWrapper cartResponseWrapper = new CartResponseWrapper(
                cartList.stream().map(CartResponseDto::new).toList()
                , totalCartPrice);

        return cartResponseWrapper;






    }

    @Transactional
    public ResponseEntity updateCart(Long id, Long userId, ProductResponseDto productResponseDto) {
        Optional<Cart> cart = cartRepository.findByIdAndUserId(id,userId);
        if(cart.isEmpty()){
            throw new EntityNotFoundException("조회하신 상품이 없습니다.");
        }

        cart.get().setCount(productResponseDto.getCount());
        cartRepository.save(cart.get());
        return ResponseEntity.status(HttpStatus.OK).body("수정이 완료되었습니다.");
    }

    public ResponseEntity deleteCart(Long id, Long userId) {
        Optional<Cart> cart = cartRepository.findByIdAndUserId(id,userId);
        if(cart.isEmpty()){
            throw new EntityNotFoundException("조회하신 상품이 없습니다.");
        }

        cartRepository.delete(cart.get());
        return ResponseEntity.status(HttpStatus.OK).body("삭제가 완료되었습니다.");

    }
}
