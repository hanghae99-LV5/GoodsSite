package com.hanghae99.goodssite.controller;

import com.hanghae99.goodssite.dto.CartRequestDto;
import com.hanghae99.goodssite.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/carts")
public class CartController {

    private final CartService cartService;

    @PostMapping("/")
    public ResponseEntity addCart(@RequestBody CartRequestDto requestDto) {
        return cartService.addCart(requestDto);
    }
}
