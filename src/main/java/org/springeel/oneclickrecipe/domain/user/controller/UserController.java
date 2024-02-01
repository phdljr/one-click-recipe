package org.springeel.oneclickrecipe.domain.user.controller;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springeel.oneclickrecipe.domain.user.dto.controller.*;
import org.springeel.oneclickrecipe.domain.user.dto.service.request.*;
import org.springeel.oneclickrecipe.domain.user.dto.service.response.UserLoginResponseDto;
import org.springeel.oneclickrecipe.domain.user.mapper.dto.UserDtoMapper;
import org.springeel.oneclickrecipe.domain.user.service.UserService;
import org.springeel.oneclickrecipe.global.security.UserDetailsImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

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

    @PutMapping("/nickname") //닉네임변경(수정)
    public ResponseEntity<?> updateNickname(
        @Valid @RequestBody NicknameUpdateControllerRequestDto controllerRequestDto,
        @AuthenticationPrincipal UserDetailsImpl userDetails
    ) {
        NicknameUpdateServiceRequestDto serviceRequestDto =
            userDtoMapper.toNicknameUpdateServiceRequestDto(controllerRequestDto);
        userService.updateNickname(userDetails.user(), serviceRequestDto);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PutMapping("/password") //비밀번호변경(수정)
    public ResponseEntity<?> updatePassword(
        @Valid @RequestBody PasswordUpdateControllerRequestDto controllerRequestDto,
        @AuthenticationPrincipal UserDetailsImpl userDetails
    ) {
        PasswordUpdateServiceRequestDto serviceRequestDto =
            userDtoMapper.toPasswordUpdateServiceRequestDto(controllerRequestDto);
        userService.updatePassword(userDetails.user(), serviceRequestDto);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
    @DeleteMapping("/withdrawl") //회원탈퇴
    public ResponseEntity<?> deleteUser(
    @Valid @RequestBody DeleteUserControllerRequestDto controllerRequestDto,
    @AuthenticationPrincipal UserDetailsImpl userDetails
    ) {
    DeleteUserServiceRequestDto serviceRequestDto =
        userDtoMapper.toDeleteUserServiceRequestDto(controllerRequestDto);
    userService.deleteUser(userDetails.user(),serviceRequestDto);
    return ResponseEntity.status(HttpStatus.OK).build();
    }
}