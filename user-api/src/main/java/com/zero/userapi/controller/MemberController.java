package com.zero.userapi.controller;

import com.zero.userapi.application.SignUpApplication;
import com.zero.userapi.domain.member.RegisterForm;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController {
    private final SignUpApplication signUpApplication;

    @PostMapping("/signup")
    public ResponseEntity<String> register(@Valid  @RequestBody RegisterForm form) {
        return ResponseEntity.ok(signUpApplication.signUp(form));
    }
}
