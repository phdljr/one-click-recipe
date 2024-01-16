package org.springeel.oneclickrecipe.domain.cart.service.impl;

import lombok.RequiredArgsConstructor;
import org.springeel.oneclickrecipe.domain.cart.repository.CartRepository;
import org.springeel.oneclickrecipe.domain.cart.service.CartService;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;
    @Override
    public void clearCart(Long userId) {
        cartRepository.deleteByUserId(userId);
    }
}
