package org.springeel.oneclickrecipe.domain.user.service.impl;

import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.springeel.oneclickrecipe.domain.user.dto.service.UserCreateServiceRequestDto;
import org.springeel.oneclickrecipe.domain.user.entity.User;
import org.springeel.oneclickrecipe.domain.user.entity.UserRole;
import org.springeel.oneclickrecipe.domain.user.exception.AlreadyExistsEmailException;
import org.springeel.oneclickrecipe.domain.user.exception.DuplicateNicknameException;
import org.springeel.oneclickrecipe.domain.user.exception.NotMatchPasswordException;
import org.springeel.oneclickrecipe.domain.user.exception.UserErrorCode;
import org.springeel.oneclickrecipe.domain.user.repository.UserRepository;
import org.springeel.oneclickrecipe.domain.user.service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    @Override
    public void signUp(final UserCreateServiceRequestDto serviceRequestDto) {
        // 이메일 중복 체크
        if(userRepository.existsByEmail(serviceRequestDto.email())){
            throw new AlreadyExistsEmailException(UserErrorCode.ALREADY_EXIST_EMAIL);
        }

        // 닉네임이 중복되지 않는지 확인
        if (userRepository.existsByNickname(serviceRequestDto.nickname())) {
            throw new DuplicateNicknameException(UserErrorCode.DUPLICATE_NICKNAME);
        }

        // 비밀번호가 같은지 확인
        if(!Objects.equals(serviceRequestDto.password(), serviceRequestDto.passwordConfirm())){
            throw new NotMatchPasswordException(UserErrorCode.NOT_MATCH_PASSWORD);
        }

        // 저장
        User user = User.builder()
            .email(serviceRequestDto.email())
            .nickname(serviceRequestDto.nickname())
            .password(passwordEncoder.encode(serviceRequestDto.password()))
            .role(UserRole.USER)
            .build();

        userRepository.save(user);
    }
}
