package org.springeel.oneclickrecipe.domain.cart.mapper.entity;

import org.mapstruct.Mapper;
import org.springeel.oneclickrecipe.domain.cart.dto.service.CartAddServiceRequestDto;
import org.springeel.oneclickrecipe.domain.cart.entity.Cart;

@Mapper(componentModel = "spring")
public interface CartEntityMapper {
    Cart toEntity(CartAddServiceRequestDto requestDto);
}
