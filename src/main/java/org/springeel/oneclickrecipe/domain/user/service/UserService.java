package org.springeel.oneclickrecipe.domain.user.service;

import jakarta.servlet.http.HttpServletResponse;
import org.springeel.oneclickrecipe.domain.user.dto.service.request.UserLoginServiceRequestDto;
import org.springeel.oneclickrecipe.domain.user.dto.service.request.UserSignUpServiceRequestDto;
import org.springeel.oneclickrecipe.domain.user.dto.service.response.UserLoginResponseDto;
import org.springeel.oneclickrecipe.domain.user.entity.User;

public interface UserService {

    void signUp(UserSignUpServiceRequestDto serviceRequestDto);

    UserLoginResponseDto login(UserLoginServiceRequestDto serviceRequestDto,
        HttpServletResponse httpServletResponse);

    UserLoginResponseDto refreshAccessToken(String refreshToken, User user,
        HttpServletResponse httpServletResponse);
}
