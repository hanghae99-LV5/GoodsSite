package com.hanghae99.goodssite.controller;

import com.hanghae99.goodssite.dto.cart.CartRequestDto;
import com.hanghae99.goodssite.dto.cart.CartResponseDto;
import com.hanghae99.goodssite.dto.cart.CartResponseWrapper;
import com.hanghae99.goodssite.dto.product.ProductResponseDto;
import com.hanghae99.goodssite.entity.User;
import com.hanghae99.goodssite.security.UserDetailsImpl;
import com.hanghae99.goodssite.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CartController {
    private final CartService cartService;


    @PostMapping("/cart")
    public ResponseEntity addCart(@RequestBody CartRequestDto cartRequestDto, @AuthenticationPrincipal UserDetailsImpl userDetails){
        Long userId = userDetails.getUser().getId();
        return cartService.addCart(cartRequestDto, userId);
    }

    @GetMapping("/cart")
    public ResponseEntity<CartResponseWrapper> getCart(@AuthenticationPrincipal UserDetailsImpl userDetails){
        Long userId = userDetails.getUser().getId();
        return new ResponseEntity<>(cartService.getCart(userId), HttpStatus.OK);
    }

    @PutMapping("/cart/{id}")
    public ResponseEntity updateCart(@PathVariable Long id, @RequestBody ProductResponseDto productResponseDto,
                                     @AuthenticationPrincipal UserDetailsImpl userDetails){
        Long userId = userDetails.getUser().getId();
        return cartService.updateCart(id,userId,productResponseDto);
    }

    @DeleteMapping("cart/{id}")
    public ResponseEntity deleteCart(@PathVariable Long id, @AuthenticationPrincipal UserDetailsImpl userDetails){
        Long userId = userDetails.getUser().getId();
        return cartService.deleteCart(id,userId);
    }


}
