package org.springeel.oneclickrecipe.domain.cart.service.impl;

import lombok.RequiredArgsConstructor;
import org.springeel.oneclickrecipe.domain.cart.entity.Cart;
import org.springeel.oneclickrecipe.domain.cart.exception.AlreadyExistsCartException;
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
        cartRepository.deleteByUserId(user.getId());
    }

    @Override
    public void addCartItem(Long userId, Long recipeFoodId) {
        // 사용자와 레시피 식재료 조회
        User user = userRepository.findById(userId)
            .orElseThrow(() -> new NotFoundCartException(CartErrorCode.NOT_FOUND_CART));
        RecipeFood recipeFood = recipeFoodRepository.findById(recipeFoodId)
            .orElseThrow(() -> new NotFoundCartException(CartErrorCode.NOT_FOUND_CART));

        /*
         * 이미 동일한 아이템이 존재하는지 확인하는 로직입니다.
         * 장바구니에 아이템 추가 전에, 장바구니 초기화 로직을 넣었으나
         * 혹시 몰라 넣은 예외처리 코드입니다.
         */
        boolean exists = cartRepository.findByUserIdAndRecipeFoodId(userId, recipeFoodId)
            .isPresent();
        if (exists) {
            throw new AlreadyExistsCartException(CartErrorCode.ALREADY_EXIST_CART);
        }

        Cart cartItem = new Cart(user, recipeFood);
        cartRepository.save(cartItem);
    }
}
