package org.springeel.oneclickrecipe.domain.cart.service.impl;

import lombok.RequiredArgsConstructor;
import org.springeel.oneclickrecipe.domain.cart.repository.CartRepository;
import org.springeel.oneclickrecipe.domain.cart.service.CartService;
import org.springeel.oneclickrecipe.domain.user.entity.User;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;

    @Override
    public void clearCart(User user) {
        // 사용자의 장바구니 데이터를 삭제
        cartRepository.deleteByUserId(user.getId());
    }
}
