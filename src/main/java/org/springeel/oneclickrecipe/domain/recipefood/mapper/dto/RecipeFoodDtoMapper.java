package org.springeel.oneclickrecipe.domain.recipefood.mapper.dto;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants.ComponentModel;
import org.springeel.oneclickrecipe.domain.recipefood.dto.controller.RecipeFoodCreateControllerRequestDto;
import org.springeel.oneclickrecipe.domain.recipefood.dto.service.RecipeFoodCreateServiceRequestDto;

@Mapper(componentModel = ComponentModel.SPRING)
public interface RecipeFoodDtoMapper {

    RecipeFoodCreateServiceRequestDto toRecipeFoodCreateServiceRequestDto(
        RecipeFoodCreateControllerRequestDto controllerRequestDto);


}
