package com.example.formlogin.service;

import com.example.formlogin.dto.UserLoginRequestDto;
import com.example.formlogin.dto.UserDto;
import com.example.formlogin.entity.User;
import com.example.formlogin.exception.UserException;
import com.example.formlogin.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    public void signUp(UserDto dto) {
        if (userRepository.existsByUserId(dto.getUserId())) {
            throw new UserException("이미 존재하는 아이디입니다.");
        }
        dto.setUserPwd(passwordEncoder.encode(dto.getUserPwd()));
        userRepository.save(User.toEntity(dto));
    }

    public String login(UserLoginRequestDto dto) {
        User user = userRepository.findByUserId(dto.getUserId())
                .orElseThrow(() -> new UserException("등록되지 않은 사용자입니다."));
        if (!passwordEncoder.matches(dto.getUserPwd(), user.getUserPwd())) {
            throw new UserException("아이디 혹은 비밀번호를 확인해주세요.");
        }
        return user.getUserId();
    }

    public UserDto getUserInfo(String userId) {
        User user = userRepository.findByUserId(userId).orElseThrow(() -> new UserException("등록되지 않은 사용자입니다."));
        return UserDto.toDto(user);
    }
}
