package com.hanghae99.goodssite.controller;

import com.hanghae99.goodssite.dto.cart.CartRequestDto;
import com.hanghae99.goodssite.dto.cart.CartResponseDto;
import com.hanghae99.goodssite.dto.cart.CartResponseWrapper;
import com.hanghae99.goodssite.security.UserDetailsImpl;
import com.hanghae99.goodssite.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/carts")
public class CartController {

    private final CartService cartService;

    @PostMapping("/")
    public ResponseEntity addCart(@RequestBody CartRequestDto requestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        Long userId = userDetails.getUser().getId();
        return cartService.addCart(requestDto, userId);
    }

    @GetMapping("/")
    public CartResponseWrapper cartList(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        Long userId = userDetails.getUser().getId();
        return cartService.cartList(userId);
    }

    @PutMapping("/{cartId}")
    public ResponseEntity editCartItem(@RequestBody CartRequestDto cartRequestDto, @PathVariable Long cartId, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        Long userId = userDetails.getUser().getId();
        return cartService.editCartItem(cartRequestDto, cartId, userId);
    }

    @DeleteMapping("/{cartId}")
    public ResponseEntity deleteCartItem(@PathVariable Long cartId) {
        return cartService.deleteCartItem(cartId);
    }
}
