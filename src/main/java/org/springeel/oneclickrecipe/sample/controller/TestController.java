package org.springeel.oneclickrecipe.sample.controller;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springeel.oneclickrecipe.sample.dto.controller.TestCreateControllerRequestDto;
import org.springeel.oneclickrecipe.sample.dto.service.TestCreateServiceRequestDto;
import org.springeel.oneclickrecipe.sample.dto.service.TestReadResponseDto;
import org.springeel.oneclickrecipe.sample.mapper.dto.TestDtoMapper;
import org.springeel.oneclickrecipe.sample.service.impl.TestServiceImpl;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class TestController {

    private final TestServiceImpl testService;
    private final TestDtoMapper testDtoMapper;

    @GetMapping("/{testId}")
    public TestReadResponseDto get(
        @PathVariable(name = "testId") Long testId
    ) {
        return testService.get(testId);
    }

    @GetMapping("/")
    public List<TestReadResponseDto> gets() {
        return testService.gets();
    }

    @PostMapping
    public void create(
        @RequestBody TestCreateControllerRequestDto controllerRequestDto
    ) {
        TestCreateServiceRequestDto serviceRequestDto = testDtoMapper.toTestServiceRequestDto(
            controllerRequestDto);
        testService.create(serviceRequestDto);
    }
}
