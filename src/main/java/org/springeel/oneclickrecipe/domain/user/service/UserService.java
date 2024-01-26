package org.springeel.oneclickrecipe.domain.user.service;

import jakarta.servlet.http.HttpServletResponse;
import org.springeel.oneclickrecipe.domain.user.dto.service.UserLoginServiceRequestDto;
import org.springeel.oneclickrecipe.domain.user.dto.service.UserSignUpServiceRequestDto;
import org.springeel.oneclickrecipe.domain.user.entity.User;

public interface UserService {

    void signUp(UserSignUpServiceRequestDto serviceRequestDto);

    void login(UserLoginServiceRequestDto serviceRequestDto,
        HttpServletResponse httpServletResponse);

    void refreshAccessToken(String refreshToken, User user, HttpServletResponse httpServletResponse);
}
