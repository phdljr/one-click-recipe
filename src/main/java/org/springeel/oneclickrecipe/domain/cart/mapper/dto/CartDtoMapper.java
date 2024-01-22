package org.springeel.oneclickrecipe.domain.cart.mapper.dto;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

import org.mapstruct.Mapper;
import org.springeel.oneclickrecipe.domain.cart.dto.controller.CartAddControllerRequestDto;
import org.springeel.oneclickrecipe.domain.cart.dto.service.request.CartAddServiceRequestDto;

@Mapper(componentModel = SPRING)
public interface CartDtoMapper {

    CartAddServiceRequestDto toServiceRequestDto(CartAddControllerRequestDto controllerRequestDto);
}
