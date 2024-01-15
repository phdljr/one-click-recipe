package org.springeel.oneclickrecipe.domain.cart.service.impl;

import lombok.RequiredArgsConstructor;
import org.springeel.oneclickrecipe.domain.cart.dto.service.CartCreateServiceRequestDto;
import org.springeel.oneclickrecipe.domain.cart.entity.Cart;
import org.springeel.oneclickrecipe.domain.cart.exception.CartErrorCode;
import org.springeel.oneclickrecipe.domain.cart.exception.NotFoundCartException;
import org.springeel.oneclickrecipe.domain.cart.mapper.entity.CartEntityMapper;
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
    private final CartEntityMapper cartEntityMapper;

    @Override
    public Cart addCart(CartCreateServiceRequestDto cartCreateServiceRequestDto, User user) {
        RecipeFood recipeFood = recipeFoodRepository.findById(
                cartCreateServiceRequestDto.recipeFoodId())
            .orElseThrow(() -> new NotFoundCartException(CartErrorCode.NOT_FOUND_CART));

        Cart cart = cartEntityMapper.toEntity(cartCreateServiceRequestDto, user, recipeFood);
        return cartRepository.save(cart);
    }
}
