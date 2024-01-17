package org.springeel.oneclickrecipe.domain.cart.service.impl;

import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springeel.oneclickrecipe.domain.cart.entity.Cart;
import org.springeel.oneclickrecipe.domain.cart.repository.CartRepository;
import org.springeel.oneclickrecipe.domain.cart.service.CartService;
import org.springeel.oneclickrecipe.domain.recipefood.entity.RecipeFood;
import org.springeel.oneclickrecipe.domain.recipefood.repository.RecipeFoodRepository;
import org.springeel.oneclickrecipe.domain.user.entity.User;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;
    private final RecipeFoodRepository recipeFoodRepository;

    @Override
    public void clearCart(User user) {
        // 사용자의 장바구니 데이터를 삭제
        cartRepository.deleteByUser(user);
    }

    @Override
    public void addCartItems(User user, List<Long> recipeFoodIds) {
        // 한 번의 쿼리로 모든 RdcipeFood 엔티티를 가져옴
        List<RecipeFood> recipeFoods = recipeFoodRepository.findAllByIdIn(recipeFoodIds);

        // 각 RecipeFood에 대해 Cart 엔티티를 생성하고 저장
        List<Cart> cartItems = recipeFoods.stream()
            .map(recipeFood -> new Cart(user, recipeFood))
            .collect(Collectors.toList());

        cartRepository.saveAll(cartItems);
    }

}
