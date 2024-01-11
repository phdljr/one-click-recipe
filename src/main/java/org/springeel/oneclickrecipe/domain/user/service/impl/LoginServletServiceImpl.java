package org.springeel.oneclickrecipe.domain.user.service.impl;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springeel.oneclickrecipe.domain.user.dto.service.UserLoginServiceRequestDto;
import org.springeel.oneclickrecipe.domain.user.entity.User;
import org.springeel.oneclickrecipe.domain.user.exception.NotFoundUserException;
import org.springeel.oneclickrecipe.domain.user.exception.UserErrorCode;
import org.springeel.oneclickrecipe.domain.user.repository.UserRepository;
import org.springeel.oneclickrecipe.domain.user.service.LoginServletService;
import org.springeel.oneclickrecipe.global.util.JwtUtil;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class LoginServletServiceImpl implements LoginServletService {

    private final JwtUtil jwtUtil;
    private final UserRepository userRepository;

    @Override
    public void addJwtToHeader(final UserLoginServiceRequestDto requestDto,
        final HttpServletResponse httpServletResponse) {
        User user = userRepository.findByEmail(requestDto.email())
            .orElseThrow(() -> new NotFoundUserException(UserErrorCode.NOT_FOUND_USER));

        String token = jwtUtil.createToken(user.getEmail(), user.getRole());

        httpServletResponse.addHeader(JwtUtil.AUTHORIZATION_HEADER, token);
    }
}
