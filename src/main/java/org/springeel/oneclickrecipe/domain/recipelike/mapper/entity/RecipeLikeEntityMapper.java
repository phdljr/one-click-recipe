package org.springeel.oneclickrecipe.domain.recipelike.mapper.entity;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.springeel.oneclickrecipe.domain.recipe.entity.Recipe;
import org.springeel.oneclickrecipe.domain.recipelike.entity.RecipeLike;
import org.springeel.oneclickrecipe.domain.user.entity.User;


@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)

public interface RecipeLikeEntityMapper {

    @Mapping(source = "user", target = "user")
    @Mapping(source = "recipe", target = "recipe")
    RecipeLike toRecipeLike(User user, Recipe recipe);
}
