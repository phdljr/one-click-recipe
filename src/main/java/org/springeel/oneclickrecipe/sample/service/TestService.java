package org.springeel.oneclickrecipe.sample.service;

import java.util.List;
import org.springeel.oneclickrecipe.domain.user.entity.User;
import org.springeel.oneclickrecipe.sample.dto.service.TestCreateServiceRequestDto;
import org.springeel.oneclickrecipe.sample.dto.service.TestReadResponseDto;

public interface TestService {

    void create(TestCreateServiceRequestDto testRequestDto, User user);

    TestReadResponseDto get(Long id);

    List<TestReadResponseDto> gets();
}
