package org.springeel.oneclickrecipe.domain.cart.mapper.entity;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springeel.oneclickrecipe.domain.cart.dto.service.CartCreateServiceRequestDto;
import org.springeel.oneclickrecipe.domain.cart.entity.Cart;
import org.springeel.oneclickrecipe.domain.recipefood.entity.RecipeFood;
import org.springeel.oneclickrecipe.domain.user.entity.User;

@Mapper(componentModel = "spring")
public interface CartEntityMapper {

//    @Mapping(source = "userId", target = "user")
//    @Mapping(source = "recipeFoodId", target = "recipeFood")
    Cart toEntity(CartCreateServiceRequestDto dto, User user, RecipeFood recipeFood);
}
