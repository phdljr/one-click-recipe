package org.springeel.oneclickrecipe.domain.recipe.mapper.entity;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants.ComponentModel;
import org.springeel.oneclickrecipe.domain.recipe.dto.service.RecipeCreateServiceRequestDto;
import org.springeel.oneclickrecipe.domain.recipe.entity.Recipe;
import org.springeel.oneclickrecipe.domain.user.entity.User;

@Mapper(componentModel = ComponentModel.SPRING)
public interface RecipeEntityMapper {

    Recipe toRecipe(RecipeCreateServiceRequestDto requestDto, User user, String folderName);

}
