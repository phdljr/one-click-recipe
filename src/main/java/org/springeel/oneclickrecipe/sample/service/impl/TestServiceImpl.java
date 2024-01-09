package org.springeel.oneclickrecipe.sample.service.impl;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springeel.oneclickrecipe.sample.dto.service.TestCreateServiceRequestDto;
import org.springeel.oneclickrecipe.sample.dto.service.TestReadResponseDto;
import org.springeel.oneclickrecipe.sample.entity.Test;
import org.springeel.oneclickrecipe.sample.mapper.TestMapper;
import org.springeel.oneclickrecipe.sample.repository.TestRepository;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class TestServiceImpl {

    private final TestRepository testRepository;

    // TestCreateServiceRequestDto -> Test
    public void create(final TestCreateServiceRequestDto testRequestDto) {
        Test test = TestMapper.INSTANCE.toTest(testRequestDto);
        testRepository.save(test);
    }

    // Test -> TestCreateServiceRequestDto
    public TestReadResponseDto get(Long id) {
        Test test = testRepository.findById(id).get();
        return TestMapper.INSTANCE.toTestReadResponseDto(test);
    }

    // List<Test> -> List<TestCreateServiceRequestDto>
    public List<TestReadResponseDto> gets() {
        List<Test> tests = testRepository.findAll();
        return TestMapper.INSTANCE.toTestReadResponseDtos(tests);
    }
}
