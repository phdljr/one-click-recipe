package org.springeel.oneclickrecipe.domain.user.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springeel.oneclickrecipe.domain.user.service.KakaoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class KakaoController {

    private final KakaoService kakaoService;

    @GetMapping("/api/v1/users/kakao/login/callback")
    public ResponseEntity<Void> kakaoLogin(@RequestParam String code, HttpServletResponse response)
        throws JsonProcessingException {
        kakaoService.kakaoLogin(code, response);
        return ResponseEntity.ok().build();
    }
}
