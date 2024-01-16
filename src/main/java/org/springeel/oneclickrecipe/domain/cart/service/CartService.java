package org.springeel.oneclickrecipe.domain.cart.service;

import org.springeel.oneclickrecipe.domain.user.entity.User;

public interface CartService {

    void clearCart(User user);
}
