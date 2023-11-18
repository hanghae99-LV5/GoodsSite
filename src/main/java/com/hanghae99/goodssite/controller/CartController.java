package com.hanghae99.goodssite.controller;

import com.hanghae99.goodssite.dto.cart.CartRequestDto;
import com.hanghae99.goodssite.security.UserDetailsImpl;
import com.hanghae99.goodssite.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

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
}
