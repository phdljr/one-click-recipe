package org.springeel.oneclickrecipe.domain.cart.controller;

import lombok.RequiredArgsConstructor;
import org.springeel.oneclickrecipe.domain.cart.dto.controller.CartCreateControllerRequestDto;
import org.springeel.oneclickrecipe.domain.cart.dto.controller.CartCreateControllerResponseDto;
import org.springeel.oneclickrecipe.domain.cart.dto.service.CartCreateServiceRequestDto;
import org.springeel.oneclickrecipe.domain.cart.dto.service.CartCreateServiceResponseDto;
import org.springeel.oneclickrecipe.domain.cart.mapper.dto.CartDtoMapper;
import org.springeel.oneclickrecipe.domain.cart.service.CartService;
import org.springeel.oneclickrecipe.global.security.UserDetailsImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/api/v1/users/{userId}/carts")
@RestController
public class CartController {

    private final CartService cartService;
    private final CartDtoMapper cartDtoMapper;

    // 장바구니 생성
    @PostMapping
    public ResponseEntity<CartCreateControllerResponseDto> addCart(
        @RequestBody CartCreateControllerRequestDto cartCreateControllerRequestDto,
        @AuthenticationPrincipal UserDetailsImpl userDetailImpl
    ) {
        CartCreateServiceRequestDto serviceRequestDto = cartDtoMapper.toServiceRequestDto(
            cartCreateControllerRequestDto);
        cartService.addCart(serviceRequestDto, userDetailImpl.user());
        return ResponseEntity.ok().build();
    }
}
