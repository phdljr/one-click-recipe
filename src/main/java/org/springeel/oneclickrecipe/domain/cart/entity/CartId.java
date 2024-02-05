package org.springeel.oneclickrecipe.domain.cart.entity;

import java.io.Serializable;
import java.util.Objects;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CartId implements Serializable {

    private Long user;
    private Long recipeFood;

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CartId cartId = (CartId) o;
        return Objects.equals(recipeFood, cartId.recipeFood) && Objects.equals(user,
            cartId.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(recipeFood, user);
    }
}
