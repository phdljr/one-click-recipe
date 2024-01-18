package org.springeel.oneclickrecipe.domain.cart.controller;

import lombok.RequiredArgsConstructor;
import org.springeel.oneclickrecipe.domain.cart.dto.controller.CartAddControllerRequestDto;
import org.springeel.oneclickrecipe.domain.cart.dto.service.CartCheckResponseDto;
import org.springeel.oneclickrecipe.domain.cart.service.CartService;
import org.springeel.oneclickrecipe.domain.user.entity.User;
import org.springeel.oneclickrecipe.global.security.UserDetailsImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/api/v1/carts")
@RestController
public class CartController {

    private final CartService cartService;

    // 장바구니 초기화
    @DeleteMapping
    public ResponseEntity<Void> clearCart(
        @AuthenticationPrincipal UserDetailsImpl userDetails
    ) {
        cartService.clearCart(userDetails.user());
        return ResponseEntity.ok().build();
    }

    // 장바구니 아이템 추가
    @PostMapping
    public ResponseEntity<Void> addCartItems(
        @RequestBody CartAddControllerRequestDto requestDto,
        @AuthenticationPrincipal UserDetailsImpl userDetails
    ) {
        // 현재 로그인한 사용자의 ID를 가져옴
        User user = userDetails.user();
        cartService.addCartItems(user, requestDto.recipeFoodIds());
        return ResponseEntity.ok().build();

    }

    // 장바구니 조회
    @GetMapping
    public ResponseEntity<CartCheckResponseDto> getCart(
        @AuthenticationPrincipal UserDetailsImpl userDetails
    ) {
        User user = userDetails.user();
        CartCheckResponseDto cartCheckResponseDto = cartService.getCart(user);
        return ResponseEntity.ok(cartCheckResponseDto);
    }
}
