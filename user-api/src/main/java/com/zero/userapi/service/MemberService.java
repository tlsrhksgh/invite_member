package com.zero.userapi.service;

import com.zero.userapi.domain.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class MemberService {
    private final MemberRepository memberRepository;

    public boolean isEmailExist(String email) {
        return memberRepository.findByEmail(email).isPresent();
    }
}
