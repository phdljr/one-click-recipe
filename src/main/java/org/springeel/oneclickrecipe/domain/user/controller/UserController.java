package org.springeel.oneclickrecipe.domain.user.controller;

import lombok.RequiredArgsConstructor;
import org.springeel.oneclickrecipe.domain.user.dto.controller.UserCreateControllerRequestDto;
import org.springeel.oneclickrecipe.domain.user.dto.service.UserCreateServiceRequestDto;
import org.springeel.oneclickrecipe.domain.user.mapper.dto.UserDtoMapper;
import org.springeel.oneclickrecipe.domain.user.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/api/v1")
@RestController
public class UserController {

    private final UserDtoMapper userDtoMapper;
    private final UserService userService;

    @PostMapping("/users/signup")
    public ResponseEntity<Void> singUp(
        @RequestBody UserCreateControllerRequestDto controllerRequestDto
    ) {
        UserCreateServiceRequestDto serviceRequestDto = userDtoMapper.toServiceRequestDto(
            controllerRequestDto);
        userService.signUp(serviceRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

}
