package org.springeel.oneclickrecipe.domain.recipefood.mapper.dto;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

import java.util.List;
import org.mapstruct.Mapper;
import org.springeel.oneclickrecipe.domain.recipefood.dto.controller.RecipeFoodCreateControllerRequestDto;
import org.springeel.oneclickrecipe.domain.recipefood.dto.controller.RecipeFoodUpdateControllerRequestDto;
import org.springeel.oneclickrecipe.domain.recipefood.dto.service.request.RecipeFoodCreateServiceRequestDto;
import org.springeel.oneclickrecipe.domain.recipefood.dto.service.request.RecipeFoodUpdateServiceRequestDto;

@Mapper(componentModel = SPRING)
public interface RecipeFoodDtoMapper {

    List<RecipeFoodCreateServiceRequestDto> toRecipeFoodCreateServiceRequestDtos(
        List<RecipeFoodCreateControllerRequestDto> controllerRequestDtos);

    RecipeFoodUpdateServiceRequestDto toRecipeFoodUpdateServiceRequestDto(
        RecipeFoodUpdateControllerRequestDto controllerRequestDto);

    RecipeFoodCreateServiceRequestDto toRecipeFoodCreateServiceRequestDto(
        RecipeFoodCreateControllerRequestDto controllerRequestDto);
}
