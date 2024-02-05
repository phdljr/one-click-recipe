package org.springeel.oneclickrecipe.global.filter;

import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springeel.oneclickrecipe.global.exception.CustomException;
import org.springeel.oneclickrecipe.global.jwt.JwtStatus;
import org.springeel.oneclickrecipe.global.jwt.JwtUtil;
import org.springeel.oneclickrecipe.global.jwt.exception.JwtErrorCode;
import org.springeel.oneclickrecipe.global.security.UserDetailsServiceImpl;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

@Slf4j(topic = "JWT 검증 및 인가")
@RequiredArgsConstructor
public class JwtAuthorizationFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;
    private final UserDetailsServiceImpl userDetailsService;

    @Override
    protected void doFilterInternal(
        @NonNull HttpServletRequest request,
        @NonNull HttpServletResponse response,
        @NonNull FilterChain filterChain
    ) throws ServletException, IOException {

        String tokenValue = jwtUtil.getAccessTokenFromHeader(request);

        if (!StringUtils.hasText(tokenValue)) {
            filterChain.doFilter(request, response);
            return;
        }

        JwtStatus accessTokenStatus = jwtUtil.validateToken(tokenValue);

        switch (accessTokenStatus) {
            case ACCESS -> setAuthentication(tokenValue);
            case EXPIRED -> throw new CustomException(JwtErrorCode.EXPIRED_TOKEN);
            case DENIED -> throw new CustomException(JwtErrorCode.INVALID_TOKEN);
        }

        filterChain.doFilter(request, response);
    }

    // 인증 처리
    public void setAuthentication(String tokenValue) {
        Claims info = jwtUtil.getUserInfoFromToken(tokenValue);

        SecurityContext context = SecurityContextHolder.createEmptyContext();
        Authentication authentication = createAuthentication(info.getSubject());
        context.setAuthentication(authentication);

        SecurityContextHolder.setContext(context);
    }

    // 인증 객체 생성
    private Authentication createAuthentication(String email) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(email);
        return new UsernamePasswordAuthenticationToken(userDetails, null,
            userDetails.getAuthorities());
    }
}