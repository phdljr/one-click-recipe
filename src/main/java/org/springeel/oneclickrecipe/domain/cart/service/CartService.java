package org.springeel.oneclickrecipe.domain.cart.service;

import java.util.List;
import org.springeel.oneclickrecipe.domain.user.entity.User;

public interface CartService {

    void clearCart(User user);

    void addCartItems(User user, List<Long> recipeFoodIds);
}
