package org.springeel.oneclickrecipe.domain.cart.controller;

import lombok.RequiredArgsConstructor;
import org.springeel.oneclickrecipe.domain.cart.dto.controller.CartAddControllerRequestDto;
import org.springeel.oneclickrecipe.domain.cart.dto.service.CartAddServiceRequestDto;
import org.springeel.oneclickrecipe.domain.cart.entity.Cart;
import org.springeel.oneclickrecipe.domain.cart.exception.CartErrorCode;
import org.springeel.oneclickrecipe.domain.cart.exception.ForbiddenAccessCartException;
import org.springeel.oneclickrecipe.domain.cart.mapper.dto.CartDtoMapper;
import org.springeel.oneclickrecipe.domain.cart.mapper.entity.CartEntityMapper;
import org.springeel.oneclickrecipe.domain.cart.service.CartService;
import org.springeel.oneclickrecipe.global.security.UserDetailsImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/api/v1/carts")
@RestController
public class CartController {

    private final CartService cartService;
    private final CartDtoMapper cartDtoMapper;
    private final CartEntityMapper cartEntityMapper;

    // 장바구니 초기화
    @DeleteMapping
    public ResponseEntity<Void> clearCart(
        @PathVariable Long userId,
        @AuthenticationPrincipal UserDetailsImpl userDetails
    ) {
        // 요청한 사용자와 로그인한 사용자가 동일한지 확인
        if (!userDetails.user().getId().equals(userId)) {
            throw new ForbiddenAccessCartException(CartErrorCode.FORBIDDEN_ACCESS_CART);
        }
        cartService.clearCart(userId);
        return ResponseEntity.ok().build();
    }

    // 장바구니 아이템 추가
    @PostMapping
    public ResponseEntity<Void> addCartItem(
        @RequestBody CartAddControllerRequestDto requestDto,
        @AuthenticationPrincipal UserDetailsImpl userDetails
    ) {
        // Dto를 엔티티
        CartAddServiceRequestDto serviceRequestDto = cartDtoMapper.toServiceDto(requestDto);
        Cart cart = cartEntityMapper.toEntity(serviceRequestDto);

        Long userId = userDetails.user().getId();
        cartService.addCartItem(userId, cart.getRecipeFood().getId());

        return ResponseEntity.ok().build();

    }
}
