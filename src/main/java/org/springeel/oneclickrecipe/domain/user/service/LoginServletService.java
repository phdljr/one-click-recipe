package org.springeel.oneclickrecipe.domain.user.service;

import jakarta.servlet.http.HttpServletResponse;
import org.springeel.oneclickrecipe.domain.user.dto.service.UserLoginServiceRequestDto;

public interface LoginServletService {

    void addJwtToHeader(UserLoginServiceRequestDto requestDto,
        HttpServletResponse httpServletResponse);

    void addJwtToCookie(UserLoginServiceRequestDto requestDto,
        HttpServletResponse httpServletResponse);
}
