package org.springeel.oneclickrecipe.domain.cart.service;

import org.springeel.oneclickrecipe.domain.cart.dto.service.request.CartAddServiceRequestDto;
import org.springeel.oneclickrecipe.domain.cart.dto.service.response.CartReadAllResponseDto;
import org.springeel.oneclickrecipe.domain.user.entity.User;

public interface CartService {

    void clearCart(User user);

    void addCartFoods(User user, CartAddServiceRequestDto recipeFoodIds);

    CartReadAllResponseDto getCart(User user); // 장바구니 조회 메소드 추가
}
