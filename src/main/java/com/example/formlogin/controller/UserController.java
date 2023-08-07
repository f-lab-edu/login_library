package com.example.formlogin.controller;

import com.example.formlogin.dto.UserLoginRequestDto;
import com.example.formlogin.dto.UserDto;
import com.example.formlogin.exception.ErrorResponse;
import com.example.formlogin.exception.UserException;
import com.example.formlogin.service.UserService;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@Slf4j
public class UserController {

    private final UserService userService;

    /**
     * 회원가입 api
     *
     * @param dto (userId, userPwd, name, age, zipCode, address, detailAddress, department)
     * @return 회원가입 성공 - 200, 회원가입 실패 - 400
     */
    @PostMapping("/signup")
    public ResponseEntity<String> signUp(@RequestBody @Validated UserDto dto) {
        userService.signUp(dto);
        return ResponseEntity.ok("회원가입에 성공하였습니다");
    }

    /**
     * 로그인 api
     *
     * @param dto (userId, userPwd)
     * @return 로그인 성공 - 200(userId)
     */
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody @Validated UserLoginRequestDto dto) {
        return ResponseEntity.ok(userService.login(dto));
    }

    /**
     * 사용자 정보 조회 api
     *
     * @param userId 사용자 id
     * @return 사용자 정보
     */
    @GetMapping("/info")
    public ResponseEntity<UserDto> info(@RequestParam String userId) {
        return ResponseEntity.ok(userService.getUserInfo(userId));
    }

    /**
     * UserException을 처리하는 메소드
     *
     * @param e UserException
     * @return 에러 내용
     */
    @ExceptionHandler(UserException.class)
    public ResponseEntity<ErrorResponse> signUpExceptionHandler(UserException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ErrorResponse(HttpStatus.BAD_REQUEST, e.getMessage()));
    }

    /**
     * MethodArgumentNotValidException을 처리하는 메소드
     *
     * @param e MethodArgumentNotValidException
     * @return 에러 내용
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ErrorResponse(HttpStatus.BAD_REQUEST,
                        Objects.requireNonNull(e.getFieldError()).getDefaultMessage()));
    }
}
