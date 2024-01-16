package org.springeel.oneclickrecipe.domain.cart.service;

public interface CartService {

    void clearCart(Long userId);

    void addCartItem(Long userId, Long recipeFoodId);
}
