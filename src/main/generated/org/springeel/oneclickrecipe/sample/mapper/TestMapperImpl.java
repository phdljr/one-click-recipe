package org.springeel.oneclickrecipe.sample.mapper;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springeel.oneclickrecipe.sample.dto.controller.TestCreateControllerRequestDto;
import org.springeel.oneclickrecipe.sample.dto.service.TestCreateServiceRequestDto;
import org.springeel.oneclickrecipe.sample.dto.service.TestReadResponseDto;
import org.springeel.oneclickrecipe.sample.entity.Test;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-01-09T21:22:44+0900",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21 (Oracle Corporation)"
)
public class TestMapperImpl implements TestMapper {

    @Override
    public Test toTest(TestCreateServiceRequestDto testRequestDto) {
        if ( testRequestDto == null ) {
            return null;
        }

        Test.TestBuilder test = Test.builder();

        test.name( testRequestDto.name() );
        test.age( testRequestDto.age() );

        return test.build();
    }

    @Override
    public TestReadResponseDto toTestReadResponseDto(Test test) {
        if ( test == null ) {
            return null;
        }

        String name = null;
        Integer age = null;

        name = test.getName();
        age = test.getAge();

        TestReadResponseDto testReadResponseDto = new TestReadResponseDto( name, age );

        return testReadResponseDto;
    }

    @Override
    public List<TestReadResponseDto> toTestReadResponseDtos(List<Test> test) {
        if ( test == null ) {
            return null;
        }

        List<TestReadResponseDto> list = new ArrayList<TestReadResponseDto>( test.size() );
        for ( Test test1 : test ) {
            list.add( toTestReadResponseDto( test1 ) );
        }

        return list;
    }

    @Override
    public TestCreateServiceRequestDto toTestServiceRequestDto(TestCreateControllerRequestDto controllerRequestDto) {
        if ( controllerRequestDto == null ) {
            return null;
        }

        String name = null;
        Integer age = null;

        name = controllerRequestDto.name();
        age = controllerRequestDto.age();

        TestCreateServiceRequestDto testCreateServiceRequestDto = new TestCreateServiceRequestDto( name, age );

        return testCreateServiceRequestDto;
    }
}
