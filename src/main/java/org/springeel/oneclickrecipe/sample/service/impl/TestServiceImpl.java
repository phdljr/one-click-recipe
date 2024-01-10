package org.springeel.oneclickrecipe.sample.service.impl;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springeel.oneclickrecipe.domain.user.entity.User;
import org.springeel.oneclickrecipe.sample.dto.service.TestCreateServiceRequestDto;
import org.springeel.oneclickrecipe.sample.dto.service.TestReadResponseDto;
import org.springeel.oneclickrecipe.sample.entity.Test;
import org.springeel.oneclickrecipe.sample.mapper.entity.TestEntityMapper;
import org.springeel.oneclickrecipe.sample.repository.TestRepository;
import org.springeel.oneclickrecipe.sample.service.TestService;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class TestServiceImpl implements TestService {

    private final TestRepository testRepository;
    private final TestEntityMapper testEntityMapper;

    // TestCreateServiceRequestDto -> Test
    @Override
    public void create(final TestCreateServiceRequestDto testRequestDto, User user) {
        Test test = testEntityMapper.toTest(testRequestDto);
        testRepository.save(test);
    }

    // Test -> TestCreateServiceRequestDto
    @Override
    public TestReadResponseDto get(Long id) {
        Test test = testRepository.findById(id).get();
        return testEntityMapper.toTestReadResponseDto(test);
    }

    // List<Test> -> List<TestCreateServiceRequestDto>
    @Override
    public List<TestReadResponseDto> gets() {
        List<Test> tests = testRepository.findAll();
        return testEntityMapper.toTestReadResponseDtos(tests);
    }
}
