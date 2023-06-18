package com.zero.userapi.service;

import com.zero.exception.MemberException;
import com.zero.model.Member;
import com.zero.model.dto.SignUpForm;
import com.zero.userapi.domain.member.SignInForm;
import com.zero.userapi.domain.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static com.zero.exception.ErrorCode.INVALID_PASSWORD;
import static com.zero.exception.ErrorCode.MEMBER_NOT_FOUND;

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
                .orElseThrow(() -> new MemberException(MEMBER_NOT_FOUND));

        if(!member.getPassword().equals(form.getPassword())) {
            throw new MemberException(INVALID_PASSWORD);
        }

        return member;
    }
}
