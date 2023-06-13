package com.zero.userapi.application;

import com.zero.userapi.domain.Member;
import com.zero.userapi.domain.member.RegisterForm;
import com.zero.userapi.domain.repository.MemberRepository;
import com.zero.userapi.exception.ErrorCode;
import com.zero.userapi.exception.UserException;
import com.zero.userapi.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class SignUpApplication {
    private final MemberRepository memberRepository;
    private final MemberService memberService;

    public String signUp(RegisterForm form) {
        if(memberService.isEmailExist(form.getEmail())) {
            throw new UserException(ErrorCode.ALREADY_REGISTER_MEMBER);
        }

        memberRepository.save(Member.from(form));
        return "회원 가입이 완료되었습니다.";
    }
}
