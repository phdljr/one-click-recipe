package org.springeel.oneclickrecipe.domain.cart.repository;

import org.springeel.oneclickrecipe.domain.cart.entity.Cart;
import org.springeel.oneclickrecipe.domain.cart.entity.CartId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

public interface CartRepository extends JpaRepository<Cart, CartId> {

    @Transactional
    void deleteByUserId(Long userId);
}
