package org.springeel.oneclickrecipe.domain.user.service;

import org.springeel.oneclickrecipe.domain.user.dto.service.UserCreateServiceRequestDto;

public interface UserService {

    void signUp(UserCreateServiceRequestDto serviceRequestDto);
}
