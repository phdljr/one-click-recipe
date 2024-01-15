package org.springeel.oneclickrecipe.domain.cart.mapper.entity;

import org.mapstruct.Mapper;
import org.springeel.oneclickrecipe.domain.cart.dto.controller.CartCreateControllerResponseDto;
import org.springeel.oneclickrecipe.domain.cart.dto.service.CartCreateServiceResponseDto;
import org.springeel.oneclickrecipe.domain.cart.entity.Cart;

@Mapper(componentModel = "spring")
public interface CartEntityMapper {

    // Entity에서 Controller용 DTO로 변환
    CartCreateControllerResponseDto entityToControllerDto(Cart entity);

    // Entity에서 Service용 DTO로 변환
    CartCreateServiceResponseDto entityToServiceDto(Cart entity);

}
