package org.springeel.oneclickrecipe.domain.cart.mapper.dto;

import org.mapstruct.Mapper;
import org.springeel.oneclickrecipe.domain.cart.dto.controller.CartCreateControllerRequestDto;
import org.springeel.oneclickrecipe.domain.cart.dto.service.CartCreateServiceRequestDto;

@Mapper(componentModel = "spring")
public interface CartDtoMapper {

    CartCreateServiceRequestDto toServiceRequestDto(
        CartCreateControllerRequestDto cartCreateControllerRequestDto);
}
