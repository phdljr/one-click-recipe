package org.springeel.oneclickrecipe.domain.user.service;

import jakarta.servlet.http.HttpServletResponse;
import org.springeel.oneclickrecipe.domain.user.dto.service.request.*;
import org.springeel.oneclickrecipe.domain.user.dto.service.response.UserLoginResponseDto;
import org.springeel.oneclickrecipe.domain.user.entity.User;

public interface UserService {

    void signUp(UserSignUpServiceRequestDto serviceRequestDto);

    UserLoginResponseDto login(UserLoginServiceRequestDto serviceRequestDto,
                               HttpServletResponse httpServletResponse);

    UserLoginResponseDto refreshAccessToken(String refreshToken, User user, HttpServletResponse httpServletResponse);

    void updateNickname(User user, NicknameUpdateServiceRequestDto serviceRequestDto);

    void updatePassword(User user, PasswordUpdateServiceRequestDto serviceRequestDto);

    void deleteUser(User user, DeleteUserServiceRequestDto serviceRequestDto);
}

