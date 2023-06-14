package com.zero.userapi.controller;

import com.zero.userapi.application.SignInApplication;
import com.zero.userapi.application.SignUpApplication;
import com.zero.userapi.domain.member.SignInForm;
import com.zero.userapi.domain.member.SignUpForm;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/member")
@RequiredArgsConstructor
@RestController
public class MemberController {
    private final SignUpApplication signUpApplication;
    private final SignInApplication signInApplication;

    @PostMapping("/signup")
    public ResponseEntity<String> signUp(@Valid  @RequestBody SignUpForm form) {
        return ResponseEntity.ok(signUpApplication.signUp(form));
    }

    @PostMapping("/signin")
    public ResponseEntity<String> signIn(@Valid @RequestBody SignInForm form) {
        return ResponseEntity.ok(signInApplication.signIn(form));
    }
}
