package org.springeel.oneclickrecipe.domain.user.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.net.URI;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springeel.oneclickrecipe.domain.user.dto.kakao.KakaoUserInfoDto;
import org.springeel.oneclickrecipe.domain.user.entity.User;
import org.springeel.oneclickrecipe.domain.user.entity.UserRole;
import org.springeel.oneclickrecipe.domain.user.repository.UserRepository;
import org.springeel.oneclickrecipe.global.util.JwtUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Slf4j(topic = "KAKAO Login")
@RequiredArgsConstructor
@Service
public class KakaoService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final RestTemplate restTemplate;

    @Value("${custom.kakao.client-id}")
    private String clientId;

    @Value("${custom.front.host}")
    private String frontHost;

    public String kakaoLogin(String code) throws JsonProcessingException {
        String accessToken = getToken(code);
        KakaoUserInfoDto kakaoUserInfo = getKakaoUserInfo(accessToken);
        User user = registerKakaoUserIfNeeded(kakaoUserInfo);
        return user.getEmail();
    }

    private String getToken(String code) throws JsonProcessingException {
        log.info("인가코드:" + code);
        // 요청 URL 만들기
        URI uri = UriComponentsBuilder
            .fromUriString("https://kauth.kakao.com")
            .path("/oauth/token")
            .encode()
            .build()
            .toUri();

        // HTTP Header 생성
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

        // HTTP Body 생성
        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
        body.add("grant_type", "authorization_code");
        body.add("client_id", clientId);
        body.add("redirect_uri", frontHost + "/login/kakao");
        body.add("code", code);

        RequestEntity<MultiValueMap<String, String>> requestEntity = RequestEntity
            .post(uri)
            .headers(headers)
            .body(body);

        // HTTP 요청 보내기
        ResponseEntity<String> response = restTemplate.exchange(
            requestEntity,
            String.class
        );

        // HTTP 응답 (JSON) -> 액세스 토큰 파싱
        JsonNode jsonNode = new ObjectMapper().readTree(response.getBody());
        return jsonNode.get("access_token").asText();
    }

    private KakaoUserInfoDto getKakaoUserInfo(String accessToken) throws JsonProcessingException {
        log.info("인가코드:" + accessToken);

        // 요청 URL 만들기
        URI uri = UriComponentsBuilder
            .fromUriString("https://kapi.kakao.com")
            .path("/v2/user/me")
            .encode()
            .build()
            .toUri();

        // HTTP Header 생성
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + accessToken);
        headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

        RequestEntity<MultiValueMap<String, String>> requestEntity = RequestEntity
            .post(uri)
            .headers(headers)
            .body(new LinkedMultiValueMap<>());

        // HTTP 요청 보내기
        ResponseEntity<String> response = restTemplate.exchange(
            requestEntity,
            String.class
        );

        JsonNode jsonNode = new ObjectMapper().readTree(response.getBody());
        Long id = jsonNode.get("id").asLong();
        String nickname = jsonNode.get("properties")
            .get("nickname").asText();
        String email = jsonNode.get("kakao_account")
            .get("email").asText();

        log.info("카카오 사용자 정보:  " + nickname + ", " + email);
        return new KakaoUserInfoDto(nickname, email);
    }

    private User registerKakaoUserIfNeeded(KakaoUserInfoDto kakaoUserInfo) {
        // DB 에 중복된 Kakao Id 가 있는지 확인
        String kakaoEmail = kakaoUserInfo.email();
        User kakaoUser = userRepository.findByEmail(kakaoEmail)
            .orElse(null);
        if (kakaoUser == null) {
            String password = UUID.randomUUID().toString();
            String encodePassword = passwordEncoder.encode(password);
            kakaoUser = User.builder()
                .email(kakaoEmail)
                .nickname(kakaoUserInfo.nickname())
                .password(encodePassword)
                .role(UserRole.USER)
                .build();
            userRepository.save(kakaoUser);
        }
        return kakaoUser;
    }
}