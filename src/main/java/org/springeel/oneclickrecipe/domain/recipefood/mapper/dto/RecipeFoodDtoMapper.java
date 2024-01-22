package org.springeel.oneclickrecipe.domain.recipefood.mapper.dto;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

import org.mapstruct.Mapper;
import org.springeel.oneclickrecipe.domain.recipefood.dto.controller.RecipeFoodCreateControllerRequestDto;
import org.springeel.oneclickrecipe.domain.recipefood.dto.controller.RecipeFoodUpdateControllerRequestDto;
import org.springeel.oneclickrecipe.domain.recipefood.dto.service.request.RecipeFoodCreateServiceRequestDto;
import org.springeel.oneclickrecipe.domain.recipefood.dto.service.request.RecipeFoodUpdateServiceRequestDto;

@Mapper(componentModel = SPRING)
public interface RecipeFoodDtoMapper {

    RecipeFoodCreateServiceRequestDto toRecipeFoodCreateServiceRequestDto(
        RecipeFoodCreateControllerRequestDto controllerRequestDto);

    RecipeFoodUpdateServiceRequestDto toRecipeFoodUpdateServiceRequestDto(
        RecipeFoodUpdateControllerRequestDto controllerRequestDto);

}
