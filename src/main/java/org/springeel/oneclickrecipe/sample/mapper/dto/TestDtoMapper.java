package org.springeel.oneclickrecipe.sample.mapper.dto;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants.ComponentModel;
import org.springeel.oneclickrecipe.sample.dto.controller.TestCreateControllerRequestDto;
import org.springeel.oneclickrecipe.sample.dto.service.TestCreateServiceRequestDto;

@Mapper(componentModel = ComponentModel.SPRING)
public interface TestDtoMapper {

    TestCreateServiceRequestDto toTestServiceRequestDto(
        TestCreateControllerRequestDto controllerRequestDto);
}
