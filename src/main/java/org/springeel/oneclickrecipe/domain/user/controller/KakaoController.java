package org.springeel.oneclickrecipe.domain.user.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springeel.oneclickrecipe.domain.user.service.KakaoService;
import org.springeel.oneclickrecipe.domain.user.service.LoginServletService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class KakaoController {

    private final KakaoService kakaoService;
    private final LoginServletService loginServletService;

    @GetMapping("/api/v1/users/kakao/login/callback")
    public ResponseEntity<Void> kakaoLogin(@RequestParam String code, HttpServletResponse response)
        throws JsonProcessingException {
        String email = kakaoService.kakaoLogin(code);
        loginServletService.addJwtToHeader(email, response);
        return ResponseEntity.ok().build();
    }
}
