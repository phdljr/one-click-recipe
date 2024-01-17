package org.springeel.oneclickrecipe.domain.cart.repository;

import java.util.List;
import org.springeel.oneclickrecipe.domain.cart.entity.Cart;
import org.springeel.oneclickrecipe.domain.cart.entity.CartId;
import org.springeel.oneclickrecipe.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart, CartId> {

    void deleteByUser(User user);

    List<Cart> findByUser(User user);
}