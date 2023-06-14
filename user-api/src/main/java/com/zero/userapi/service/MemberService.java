package com.zero.userapi.service;

import com.zero.userapi.domain.Member;
import com.zero.userapi.domain.member.SignInForm;
import com.zero.userapi.domain.member.SignUpForm;
import com.zero.userapi.domain.repository.MemberRepository;
import com.zero.userapi.exception.UserException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static com.zero.userapi.exception.ErrorCode.MEMBER_NOT_FOUND;
import static com.zero.userapi.exception.ErrorCode.PASSWORD_NOT_VALID;

@RequiredArgsConstructor
@Service
public class MemberService {
    private final MemberRepository memberRepository;

    public void createMember(SignUpForm form) {
        memberRepository.save(Member.from(form));
    }

    public boolean isEmailExist(String email) {
        return memberRepository.findByEmail(email).isPresent();
    }

    public Member findValidMember(SignInForm form) {
        Member member = memberRepository.findByEmail(form.getEmail())
                .orElseThrow(() -> new UserException(MEMBER_NOT_FOUND));

        if(!member.getPassword().equals(form.getPassword())) {
            throw new UserException(PASSWORD_NOT_VALID);
        }

        return member;
    }
}
