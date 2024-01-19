package org.springeel.oneclickrecipe.domain.cart.mapper.dto;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springeel.oneclickrecipe.domain.cart.dto.controller.CartAddControllerRequestDto;
import org.springeel.oneclickrecipe.domain.cart.dto.service.CartAddServiceRequestDto;

@Mapper(componentModel = "spring")
public interface CartDtoMapper {

    @Mapping(target = "userId", ignore = true)
    CartAddServiceRequestDto toServiceDto(CartAddControllerRequestDto controllerRequestDto);
}
