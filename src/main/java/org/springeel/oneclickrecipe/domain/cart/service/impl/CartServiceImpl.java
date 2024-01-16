package org.springeel.oneclickrecipe.domain.cart.service.impl;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springeel.oneclickrecipe.domain.cart.entity.Cart;
import org.springeel.oneclickrecipe.domain.cart.exception.CartErrorCode;
import org.springeel.oneclickrecipe.domain.cart.exception.NotFoundCartException;
import org.springeel.oneclickrecipe.domain.cart.repository.CartRepository;
import org.springeel.oneclickrecipe.domain.cart.service.CartService;
import org.springeel.oneclickrecipe.domain.recipefood.entity.RecipeFood;
import org.springeel.oneclickrecipe.domain.recipefood.repository.RecipeFoodRepository;
import org.springeel.oneclickrecipe.domain.user.entity.User;
import org.springeel.oneclickrecipe.domain.user.repository.UserRepository;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;
    private final RecipeFoodRepository recipeFoodRepository;
    private final UserRepository userRepository;

    @Override
    public void clearCart(User user) {
        // 사용자의 장바구니 데이터를 삭제
        cartRepository.deleteByUserId(user);
    }

    @Override
    public void addCartItems(Long userId, List<Long> recipeFoodIds) {
        User user = userRepository.findById(userId)
            .orElseThrow(() -> new NotFoundCartException(CartErrorCode.NOT_FOUND_CART));

        for (Long recipeFoodId : recipeFoodIds) {
            addCartItem(user, recipeFoodId);
        }
    }

    private void addCartItem(User user, Long recipeFoodId) {
        RecipeFood recipeFood = recipeFoodRepository.findById(recipeFoodId)
            .orElseThrow(() -> new NotFoundCartException(CartErrorCode.NOT_FOUND_CART));

        // 장바구니 아이템 추가
        Cart cartItem = new Cart(user, recipeFood);
        cartRepository.save(cartItem);
    }
}
