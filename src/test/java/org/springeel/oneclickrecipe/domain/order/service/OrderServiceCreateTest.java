package org.springeel.oneclickrecipe.domain.order.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springeel.oneclickrecipe.domain.cart.entity.Cart;
import org.springeel.oneclickrecipe.domain.cart.repository.CartRepository;
import org.springeel.oneclickrecipe.domain.food.entity.Food;
import org.springeel.oneclickrecipe.domain.food.entity.UnitType;
import org.springeel.oneclickrecipe.domain.recipefood.entity.RecipeFood;
import org.springeel.oneclickrecipe.domain.user.entity.User;
import org.springeel.oneclickrecipe.domain.user.entity.UserRole;

@ExtendWith(MockitoExtension.class)
public class OrderServiceCreateTest {

    @Mock
    private CartRepository cartRepository;
    private User user;
    private List<Cart> cartItems;

    @BeforeEach
    void setUp() {
        user = new User("test@example.com", "password123", "TestUser", UserRole.USER);
        cartItems = Arrays.asList(
            new Cart(user,
                new RecipeFood("김치", (short) 5, null, new Food("두부", 10f, UnitType.COUNT))));
        when(cartRepository.findByUser(user)).thenReturn(cartItems);
    }

    @Test
    @DisplayName("장바구니 아이템 조회")
    public void searchCart() {
        // when
        List<Cart> result = cartRepository.findByUser(user);

        // then
        assertNotNull(result, "장바구니 아이템은 null이 아니어야 합니다.");
        assertFalse(result.isEmpty(), "장바구니는 비워져 있으면 안됩니다.");
        assertEquals(cartItems.size(), result.size(), "장바구니 아이템 개수가 일치해야 합니다.");
    }

}
