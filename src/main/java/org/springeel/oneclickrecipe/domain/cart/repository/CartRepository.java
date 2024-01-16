package org.springeel.oneclickrecipe.domain.cart.repository;

import java.util.Optional;
import org.springeel.oneclickrecipe.domain.cart.entity.Cart;
import org.springeel.oneclickrecipe.domain.cart.entity.CartId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface CartRepository extends JpaRepository<Cart, CartId> {

    @Transactional
    void deleteByUserId(Long userId);

    // 특정 사용자의 장바구니에서 특정 레시피_식재료를 찾는 메소드
    Optional<Cart> findByUserIdAndRecipeFoodId(Long userId, Long recipeFoodId);
}
