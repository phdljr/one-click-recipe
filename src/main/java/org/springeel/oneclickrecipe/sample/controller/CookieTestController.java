package org.springeel.oneclickrecipe.sample.controller;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class CookieTestController {

    @GetMapping("/tests/create-cookie")
    public void createCookie(HttpServletResponse httpServletResponse){
        Cookie cookie = new Cookie("http-only-cookie", "test");
        cookie.setHttpOnly(true);
        cookie.setPath("/");
        cookie.setMaxAge(1000000);
        httpServletResponse.addCookie(cookie);
    }

    @GetMapping("/tests/delete-cookie")
    public void deleteCookie(HttpServletResponse httpServletResponse){
        Cookie cookie = new Cookie("http-only-cookie", "");
        cookie.setHttpOnly(true);
        cookie.setPath("/");
        cookie.setMaxAge(0);
        httpServletResponse.addCookie(cookie);
    }
}
