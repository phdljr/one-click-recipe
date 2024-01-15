package org.springeel.oneclickrecipe.domain.cart.mapper.dto;

import org.mapstruct.Mapper;
import org.springeel.oneclickrecipe.domain.cart.dto.controller.CartCreateControllerRequestDto;
import org.springeel.oneclickrecipe.domain.cart.dto.service.CartCreateServiceRequestDto;
import org.springeel.oneclickrecipe.domain.cart.entity.Cart;

@Mapper(componentModel = "spring")
public interface CartDtoMapper {

    // Controller용 DTO에서 Entity로 변환
    Cart dtoToEntity(CartCreateControllerRequestDto dto);

    // Service용 DTO에서 Entity로 변환
    Cart dtoToEntity(CartCreateServiceRequestDto dto);
}
