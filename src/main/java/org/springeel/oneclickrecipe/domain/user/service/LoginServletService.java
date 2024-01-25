package org.springeel.oneclickrecipe.domain.user.service;

import jakarta.servlet.http.HttpServletResponse;
import org.springeel.oneclickrecipe.domain.user.dto.service.UserLoginServiceRequestDto;

public interface LoginServletService {

    void addJwtToHeader(String email,
        HttpServletResponse httpServletResponse);

    void addJwtToCookie(String email,
        HttpServletResponse httpServletResponse);
}
