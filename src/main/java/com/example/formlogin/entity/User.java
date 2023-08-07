package com.example.formlogin.entity;

import com.example.formlogin.dto.UserDto;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String userId;

    private String userPwd;

    private String name;

    private int age;

    private String address;

    private String detailAddress;

    private String zipCode;

    private String department;

    public User(String userId, String userPwd, String name, int age, String address, String detailAddress,
                String zipCode,
                String department) {
        this.userId = userId;
        this.userPwd = userPwd;
        this.name = name;
        this.age = age;
        this.address = address;
        this.detailAddress = detailAddress;
        this.zipCode = zipCode;
        this.department = department;
    }

    public static User toEntity(UserDto dto) {
        return new User(dto.getUserId(), dto.getUserPwd(), dto.getName(), dto.getAge(), dto.getAddress(),
                dto.getDetailAddress(), dto.getZipCode(), dto.getDepartment());
    }
}
