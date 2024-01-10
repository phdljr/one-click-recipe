package org.springeel.oneclickrecipe.sample.service.impl;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springeel.oneclickrecipe.sample.dto.service.TestCreateServiceRequestDto;
import org.springeel.oneclickrecipe.sample.dto.service.TestReadResponseDto;
import org.springeel.oneclickrecipe.sample.entity.Test;
import org.springeel.oneclickrecipe.sample.mapper.entity.TestEntityMapper;
import org.springeel.oneclickrecipe.sample.repository.TestRepository;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class TestServiceImpl {

    private final TestRepository testRepository;
    private final TestEntityMapper testEntityMapper;

    // TestCreateServiceRequestDto -> Test
    public void create(final TestCreateServiceRequestDto testRequestDto) {
        Test test = testEntityMapper.toTest(testRequestDto);
        testRepository.save(test);
    }

    // Test -> TestCreateServiceRequestDto
    public TestReadResponseDto get(Long id) {
        Test test = testRepository.findById(id).get();
        return testEntityMapper.toTestReadResponseDto(test);
    }

    // List<Test> -> List<TestCreateServiceRequestDto>
    public List<TestReadResponseDto> gets() {
        List<Test> tests = testRepository.findAll();
        return testEntityMapper.toTestReadResponseDtos(tests);
    }
}
