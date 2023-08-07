package com.example.formlogin.dto;

import com.example.formlogin.entity.User;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

    @NotBlank(message = "아이디를 입력해주세요.")
    private String userId;

    @NotBlank(message = "비밀번호를 입력해주세요.")
    private String userPwd;

    @NotBlank(message = "이름을 입력해주세요.")
    private String name;

    private int age;

    private String address;

    private String detailAddress;

    private String zipCode;

    private String department;

    public static UserDto toDto(User user) {
        return UserDto.builder()
                .userId(user.getUserId())
                .userPwd(user.getUserPwd())
                .name(user.getName())
                .age(user.getAge())
                .address(user.getAddress())
                .detailAddress(user.getDetailAddress())
                .zipCode(user.getZipCode())
                .department(user.getDepartment())
                .build();
    }
}
