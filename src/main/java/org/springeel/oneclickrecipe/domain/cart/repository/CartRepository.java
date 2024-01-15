package org.springeel.oneclickrecipe.domain.cart.repository;

import org.springeel.oneclickrecipe.domain.cart.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart, Long> {

}
