package org.springeel.oneclickrecipe.domain.recipelike.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springeel.oneclickrecipe.domain.recipe.entity.Recipe;
import org.springeel.oneclickrecipe.domain.user.entity.User;
import org.springeel.oneclickrecipe.global.entity.BaseEntity;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "TB_RECIPE_LIKE")
@Entity
@IdClass(RecipeLikeId.class)
public class RecipeLike extends BaseEntity {

    @Id
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Id
    @ManyToOne
    @JoinColumn(name = "recipe_id")
    private Recipe recipe;

    @Builder
    public RecipeLike(
        final User user,
        final Recipe recipe
    ) {
        this.user = user;
        this.recipe = recipe;
    }
}
