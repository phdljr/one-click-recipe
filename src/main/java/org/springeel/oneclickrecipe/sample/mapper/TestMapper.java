package org.springeel.oneclickrecipe.sample.mapper;

import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springeel.oneclickrecipe.sample.dto.controller.TestCreateControllerRequestDto;
import org.springeel.oneclickrecipe.sample.dto.service.TestCreateServiceRequestDto;
import org.springeel.oneclickrecipe.sample.dto.service.TestReadResponseDto;
import org.springeel.oneclickrecipe.sample.entity.Test;

@Mapper
public interface TestMapper {

    TestMapper INSTANCE = Mappers.getMapper(TestMapper.class);

    Test toTest(TestCreateServiceRequestDto testRequestDto);

    TestReadResponseDto toTestReadResponseDto(Test test);

    List<TestReadResponseDto> toTestReadResponseDtos(List<Test> test);

    TestCreateServiceRequestDto toTestServiceRequestDto(
        TestCreateControllerRequestDto controllerRequestDto);
}
