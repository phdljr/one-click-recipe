package org.springeel.oneclickrecipe.domain.cart.service.impl;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springeel.oneclickrecipe.domain.cart.dto.service.request.CartAddServiceRequestDto;
import org.springeel.oneclickrecipe.domain.cart.dto.service.response.CartReadAllResponseDto;
import org.springeel.oneclickrecipe.domain.cart.dto.service.response.CartReadResponseDto;
import org.springeel.oneclickrecipe.domain.cart.entity.Cart;
import org.springeel.oneclickrecipe.domain.cart.repository.CartRepository;
import org.springeel.oneclickrecipe.domain.cart.service.CartService;
import org.springeel.oneclickrecipe.domain.recipefood.entity.RecipeFood;
import org.springeel.oneclickrecipe.domain.recipefood.repository.RecipeFoodRepository;
import org.springeel.oneclickrecipe.domain.user.entity.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;
    private final RecipeFoodRepository recipeFoodRepository;

    @Transactional
    @Override
    public void clearCart(User user) {
        // 사용자의 장바구니 데이터를 삭제
        cartRepository.deleteByUser(user);
    }

    @Override
    public void addCartFoods(User user, CartAddServiceRequestDto recipeFoodIds) {
        // 한 번의 쿼리로 모든 RdcipeFood 엔티티를 가져옴
        List<RecipeFood> recipeFoods = recipeFoodRepository.findAllById(
            recipeFoodIds.recipeFoodIds());

        // 각 RecipeFood에 대해 Cart 엔티티를 생성하고 저장
        List<Cart> cartItems = recipeFoods.stream()
            .map(recipeFood -> Cart.builder()
                .user(user)
                .recipeFood(recipeFood)
                .build())
            .toList();

        cartRepository.saveAll(cartItems);
    }

    @Override
    public CartReadAllResponseDto getCart(User user) {
        List<Cart> carts = cartRepository.findAllByUser(user);
        int totalPrice = carts.stream()
            .mapToInt(cart -> cart.getRecipeFood().getFood().getPrice() * cart.getRecipeFood()
                .getAmount())
            .sum();

        List<CartReadResponseDto> foods = carts.stream()
            .map(cart -> CartReadResponseDto.builder()
                .id(cart.getRecipeFood().getId())
                .name(cart.getRecipeFood().getFood().getName())
                .quantity(cart.getRecipeFood().getAmount())
                .price(cart.getRecipeFood().getAmount() * cart.getRecipeFood().getFood().getPrice())
                .build())
            .toList();
        return CartReadAllResponseDto.builder()
            .totalPrice(totalPrice)
            .foods(foods)
            .build();
    }

}
