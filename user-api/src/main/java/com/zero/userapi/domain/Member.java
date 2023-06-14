package com.zero.userapi.domain;

import com.zero.userapi.domain.member.SignUpForm;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String email;
    private String phoneNumber;
    private String password;

    public static Member from(SignUpForm form) {
        return Member.builder()
                .email(form.getEmail())
                .name(form.getName())
                .password(form.getPassword())
                .phoneNumber(form.getPhoneNumber())
                .build();
    }
}
