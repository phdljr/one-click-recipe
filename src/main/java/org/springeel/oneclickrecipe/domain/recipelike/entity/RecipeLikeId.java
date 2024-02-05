package org.springeel.oneclickrecipe.domain.recipelike.entity;

import java.io.Serializable;
import java.util.Objects;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RecipeLikeId implements Serializable {

    private Long recipe;
    private Long user;

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        RecipeLikeId that = (RecipeLikeId) o;
        return Objects.equals(recipe, that.recipe) && Objects.equals(user,
            that.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(recipe, user);
    }
}
