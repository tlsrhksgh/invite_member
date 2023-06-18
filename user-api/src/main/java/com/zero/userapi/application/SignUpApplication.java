package com.zero.userapi.application;

import com.zero.exception.MemberException;
import com.zero.model.dto.SignUpForm;
import com.zero.userapi.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static com.zero.exception.ErrorCode.ALREADY_REGISTER_MEMBER;

@RequiredArgsConstructor
@Service
public class SignUpApplication {
    private final MemberService memberService;

    public String signUp(SignUpForm form) {
        if(memberService.isEmailExist(form.getEmail())) {
            throw new MemberException(ALREADY_REGISTER_MEMBER);
        }

        memberService.createMember(form);

        return "회원 가입이 완료되었습니다.";
    }
}
