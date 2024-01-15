package org.springeel.oneclickrecipe.domain.cart.service;

import org.springeel.oneclickrecipe.domain.cart.dto.service.CartCreateServiceRequestDto;
import org.springeel.oneclickrecipe.domain.cart.entity.Cart;
import org.springeel.oneclickrecipe.domain.user.entity.User;

public interface CartService {

    Cart addCart(CartCreateServiceRequestDto cartCreateServiceRequestDto, User user);
}
