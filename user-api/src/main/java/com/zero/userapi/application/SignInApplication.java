package com.zero.userapi.application;

import com.zero.config.JwtAuthenticationProvider;
import com.zero.exception.MemberException;
import com.zero.model.Member;
import com.zero.userapi.domain.member.SignInForm;
import com.zero.userapi.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static com.zero.exception.ErrorCode.MEMBER_NOT_FOUND;

@RequiredArgsConstructor
@Service
public class SignInApplication {
    private final MemberService memberService;
    private final JwtAuthenticationProvider jwtProvider;

    public String signIn(SignInForm form) {
        Member member = memberService.findValidMember(form);

        if(member == null) {
            throw new MemberException(MEMBER_NOT_FOUND);
        }

        return jwtProvider.createToken(form.getEmail());
    }
}
