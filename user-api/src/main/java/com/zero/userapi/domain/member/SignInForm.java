package com.zero.userapi.domain.member;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class SignInForm {
    @NotBlank
    private String email;
    @NotBlank
    private String password;
}
