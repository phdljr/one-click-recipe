package org.springeel.oneclickrecipe.domain.user.service;

import org.springeel.oneclickrecipe.domain.user.dto.service.UserLoginServiceRequestDto;
import org.springeel.oneclickrecipe.domain.user.dto.service.UserSignUpServiceRequestDto;

public interface UserService {

    void signUp(UserSignUpServiceRequestDto serviceRequestDto);

    void login(UserLoginServiceRequestDto serviceRequestDto);
}
