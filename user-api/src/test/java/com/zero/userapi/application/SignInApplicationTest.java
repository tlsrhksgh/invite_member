package com.zero.userapi.application;

import com.zero.config.JwtAuthenticationProvider;
import com.zero.userapi.domain.Member;
import com.zero.userapi.domain.member.SignInForm;
import com.zero.userapi.exception.MemberException;
import com.zero.userapi.service.MemberService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class SignInApplicationTest {
    @Mock
    private MemberService memberService;

    @Mock
    private JwtAuthenticationProvider jwtProvider;

    @InjectMocks
    private SignInApplication signInApplication;

    @Test
    @DisplayName("로그인 성공 후 토큰 발급")
    void signIn_CreateToken_MemberLoginSuccess() {
        //given
        SignInForm form = new SignInForm("test@naver.com", "1234");

        Member member = Member.builder()
                .email(form.getEmail())
                .password(form.getPassword())
                .name("test")
                .phoneNumber("01012341234")
                .build();

        given(memberService.findValidMember(form))
                .willReturn(member);
        given(jwtProvider.createToken(form.getEmail()))
                .willReturn("temptoken");

        //when
        String token = signInApplication.signIn(form);

        //then
        assertEquals(token, "temptoken");
    }

    @Test
    @DisplayName("가입되지 않은 회원 토큰 발급 실패")
    void signIn_UnregisteredMember_ThrowException() {
        //given
        SignInForm form = new SignInForm("test@naver.com", "1234");

        given(memberService.findValidMember(form))
                .willReturn(null);

        //when
        Exception exception = assertThrows(MemberException.class, () -> signInApplication.signIn(form));

        //then
        assertEquals(exception.getMessage(), "가입되지 않은 회원입니다.");
    }
}