package org.springeel.oneclickrecipe.sample.mapper.entity;

import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants.ComponentModel;
import org.springeel.oneclickrecipe.sample.dto.service.TestCreateServiceRequestDto;
import org.springeel.oneclickrecipe.sample.dto.service.TestReadResponseDto;
import org.springeel.oneclickrecipe.sample.entity.Test;

@Mapper(componentModel = ComponentModel.SPRING)
public interface TestEntityMapper {

    Test toTest(TestCreateServiceRequestDto testRequestDto);

    TestReadResponseDto toTestReadResponseDto(Test test);

    List<TestReadResponseDto> toTestReadResponseDtos(List<Test> test);
}
