package com.zero.userapi.domain.member;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class SignUpForm {
    @NotBlank(message = "이름 입력은 필수입니다.")
    private String name;

    @NotBlank(message = "비밀번호 입력은 필수입니다.")
    private String password;

    @NotBlank(message = "이메일 입력은 필수입니다.")
    private String email;

    @NotBlank(message = "핸드폰 번호 입력은 필수입니다.")
    private String phoneNumber;
}
