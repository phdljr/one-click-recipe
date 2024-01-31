package org.springeel.oneclickrecipe.domain.user.controller;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springeel.oneclickrecipe.domain.user.dto.controller.UserLoginControllerRequestDto;
import org.springeel.oneclickrecipe.domain.user.dto.controller.UserSignUpControllerRequestDto;
import org.springeel.oneclickrecipe.domain.user.dto.service.request.UserLoginServiceRequestDto;
import org.springeel.oneclickrecipe.domain.user.dto.service.request.UserSignUpServiceRequestDto;
import org.springeel.oneclickrecipe.domain.user.dto.service.response.UserLoginResponseDto;
import org.springeel.oneclickrecipe.domain.user.mapper.dto.UserDtoMapper;
import org.springeel.oneclickrecipe.domain.user.service.UserService;
import org.springeel.oneclickrecipe.global.security.UserDetailsImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
@RestController
public class UserController {

    private final UserDtoMapper userDtoMapper;
    private final UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<Void> singUp(
        @Valid @RequestBody UserSignUpControllerRequestDto controllerRequestDto
    ) {
        UserSignUpServiceRequestDto serviceRequestDto = userDtoMapper.toUserSignUpServiceRequestDto(
            controllerRequestDto);
        userService.signUp(serviceRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/login")
    public ResponseEntity<UserLoginResponseDto> login(
        @Valid @RequestBody UserLoginControllerRequestDto controllerRequestDto,
        HttpServletResponse httpServletResponse
    ) {
        UserLoginServiceRequestDto serviceRequestDto = userDtoMapper.toUserLoginServiceRequestDto(
            controllerRequestDto);
        UserLoginResponseDto responseDto = userService.login(serviceRequestDto,
            httpServletResponse);
        return ResponseEntity.status(HttpStatus.OK).body(responseDto);
    }

    @PostMapping("/refresh")
    public ResponseEntity<UserLoginResponseDto> refreshAccessToken(
        @RequestHeader("Authorization") String refreshToken,
        @AuthenticationPrincipal UserDetailsImpl userDetails,
        HttpServletResponse httpServletResponse
    ) {
        UserLoginResponseDto responseDto = userService.refreshAccessToken(refreshToken,
            userDetails.user(), httpServletResponse);
        return ResponseEntity.ok(responseDto);
    }
}
