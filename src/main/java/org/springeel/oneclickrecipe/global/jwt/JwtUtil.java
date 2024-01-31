package org.springeel.oneclickrecipe.global.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.Jwts.SIG;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SecureDigestAlgorithm;
import io.jsonwebtoken.security.SignatureException;
import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.Base64;
import java.util.Date;
import javax.crypto.SecretKey;
import lombok.extern.slf4j.Slf4j;
import org.springeel.oneclickrecipe.domain.user.entity.User;
import org.springeel.oneclickrecipe.domain.user.entity.UserRole;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Slf4j(topic = "JwtUtil")
@Component
public class JwtUtil {

    public static final String ACCESS_TOKEN_HEADER = "Access-Token";
    public static final String REFRESH_TOKEN_HEADER = "Refresh-Token";
    public static final String AUTHORIZATION_HEADER = "Authorization";
    public static final String AUTHORIZATION_KEY = "auth";
    public static final String BEARER_PREFIX = "Bearer ";

    private final SecureDigestAlgorithm<SecretKey, SecretKey> signatureAlgorithm = SIG.HS256;

    @Value("${custom.jwt.access.expiration-period}")
    private long accessTokenExpirationPeriod;

    @Value("${custom.jwt.refresh.expiration-period}")
    private long refreshTokenExpirationPeriod;

    @Value("${custom.jwt.secret-key}")
    private String secretKey;

    private SecretKey key;

    @PostConstruct
    public void init() {
        byte[] bytes = Base64.getDecoder().decode(secretKey);
        key = Keys.hmacShaKeyFor(bytes);
    }

    public String createAccessToken(String email, UserRole role) {
        Date now = new Date();

        return BEARER_PREFIX +
            Jwts.builder()
                .subject(email) // 사용자 식별자값(ID)
                .claim(AUTHORIZATION_KEY, role)
                .expiration(new Date(now.getTime() + accessTokenExpirationPeriod))
                .issuedAt(now) // 발급일
                .signWith(key, signatureAlgorithm) // 암호화 알고리즘
                .compact();
    }

    public String createRefreshToken(String email, UserRole role) {
        Date now = new Date();

        return BEARER_PREFIX +
            Jwts.builder()
                .subject(email) // 사용자 식별자값(ID)
                .claim(AUTHORIZATION_KEY, role)
                .expiration(new Date(now.getTime() + refreshTokenExpirationPeriod))
                .issuedAt(now) // 발급일
                .signWith(key, signatureAlgorithm) // 암호화 알고리즘
                .compact();
    }

    // header 에서 JWT 가져오기
    public String getAccessTokenFromHeader(HttpServletRequest request) {
        String bearerToken = request.getHeader(AUTHORIZATION_HEADER);
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith(BEARER_PREFIX)) {
            return bearerToken.substring(7);
        }
        return null;
    }

    // 토큰 검증
    public JwtStatus validateToken(String token) {
        try {
            Jwts.parser().verifyWith(key).build().parseSignedClaims(token);
            return JwtStatus.ACCESS;
        } catch (ExpiredJwtException e) {
            log.error("Expired JWT token, 만료된 JWT token 입니다.");
            return JwtStatus.EXPIRED;
        } catch (SecurityException | MalformedJwtException | SignatureException e) {
            log.error("Invalid JWT signature, 유효하지 않는 JWT 서명 입니다.");
        } catch (UnsupportedJwtException e) {
            log.error("Unsupported JWT token, 지원되지 않는 JWT 토큰 입니다.");
        } catch (IllegalArgumentException e) {
            log.error("JWT claims is empty, 잘못된 JWT 토큰 입니다.");
        }
        return JwtStatus.DENIED;
    }

    // 토큰에서 사용자 정보 가져오기
    public Claims getUserInfoFromToken(String token) {
        return Jwts.parser()
            .verifyWith(key)
            .build()
            .parseSignedClaims(token)
            .getPayload();
    }

    public void addJwtToHeader(
        final User user,
        final HttpServletResponse httpServletResponse
    ) {
        addAccessTokenToHeader(user, httpServletResponse);
        addRefreshTokenToHeader(user, httpServletResponse);
    }

    public void addAccessTokenToHeader(
        final User user,
        final HttpServletResponse httpServletResponse
    ) {
        String accessToken = createAccessToken(user.getEmail(), user.getRole());
        log.info("accessToken 생성 {}", accessToken);
        httpServletResponse.addHeader(ACCESS_TOKEN_HEADER, accessToken);
    }

    public void addRefreshTokenToHeader(
        final User user,
        final HttpServletResponse httpServletResponse
    ) {
        String refreshToken = createRefreshToken(user.getEmail(), user.getRole());
        log.info("refreshToken 생성 {}", refreshToken);
        httpServletResponse.addHeader(REFRESH_TOKEN_HEADER, refreshToken);
    }
}