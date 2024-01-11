package org.springeel.oneclickrecipe.domain.user.mapper.entity;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class PasswordTranslator {

    private final PasswordEncoder passwordEncoder;

    @EncodePassword
    public String encodePassword(String password) {
        return passwordEncoder.encode(password);
    }
}
