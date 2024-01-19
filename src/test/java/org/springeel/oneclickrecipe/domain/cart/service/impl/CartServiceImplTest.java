package org.springeel.oneclickrecipe.domain.cart.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springeel.oneclickrecipe.domain.cart.dto.service.CartReadAllResponseDto;
import org.springeel.oneclickrecipe.domain.cart.entity.Cart;
import org.springeel.oneclickrecipe.domain.cart.repository.CartRepository;
import org.springeel.oneclickrecipe.domain.food.entity.Food;
import org.springeel.oneclickrecipe.domain.food.entity.UnitType;
import org.springeel.oneclickrecipe.domain.recipe.entity.Recipe;
import org.springeel.oneclickrecipe.domain.recipefood.entity.RecipeFood;
import org.springeel.oneclickrecipe.domain.user.entity.User;
import org.springeel.oneclickrecipe.domain.user.entity.UserRole;

@ExtendWith(MockitoExtension.class)
public class CartServiceImplTest {

    @Mock
    private CartRepository cartRepository;

    @InjectMocks
    private CartServiceImpl cartService;

    @Test
    public void getCart_ShouldReturnCartDetails() {
        // given
        User testUser = new User("배규태", "12345678", "봄장어", UserRole.USER);
        RecipeFood recipeFood1 = new RecipeFood((short) 500, Recipe.builder().build(),
            new Food("김치", 10000, UnitType.G));
        RecipeFood recipeFood2 = new RecipeFood((short) 3, Recipe.builder().build(),
            new Food("두부", 12000, UnitType.COUNT));
        List<Cart> testCarts = Arrays.asList(new Cart(testUser, recipeFood1),
            new Cart(testUser, recipeFood2));
        when(cartRepository.findAllByUser(testUser)).thenReturn(testCarts);

        // when
        CartReadAllResponseDto result = cartService.getCart(testUser);

        // then
        assertNotNull(result);
        assertEquals(22000, result.totalPrice());
    }
}