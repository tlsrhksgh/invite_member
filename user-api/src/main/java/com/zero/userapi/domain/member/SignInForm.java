package com.zero.userapi.domain.member;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class SignInForm {
    @NotBlank
    private String email;
    @NotBlank
    private String password;
}
