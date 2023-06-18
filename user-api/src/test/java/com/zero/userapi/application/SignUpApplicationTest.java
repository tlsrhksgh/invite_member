package com.zero.userapi.application;

import com.zero.exception.MemberException;
import com.zero.model.dto.SignUpForm;
import com.zero.userapi.service.MemberService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class SignUpApplicationTest {
    @Mock
    private MemberService memberService;

    @InjectMocks
    private SignUpApplication signUpApplication;

    @Test
    @DisplayName("회원가입 성공")
    void signUp_SignUpSuccess() {
        //given
        SignUpForm form = SignUpForm.builder()
                .email("test")
                .password("1234")
                .phoneNumber("01012341234")
                .name("test")
                .build();

        given(memberService.isEmailExist(form.getEmail()))
                .willReturn(false);
        willDoNothing().given(memberService).createMember(form);

        //when
        String message = signUpApplication.signUp(form);

        //then
        assertEquals(message, "회원 가입이 완료되었습니다.");
        verify(memberService, times(1)).isEmailExist(form.getEmail());
        verify(memberService, times(1)).createMember(form);
    }

    @Test
    @DisplayName("이미 가입되어 있는 회원으로 가입 실패")
    void signUp_AlreadySignUpMember_ThrowMemberException() {
        //given
        SignUpForm form = SignUpForm.builder()
                .email("test@naver.com")
                .password("1234")
                .phoneNumber("01012341234")
                .name("test")
                .build();

        given(memberService.isEmailExist(form.getEmail()))
                .willReturn(true);

        //when
        //then
        assertThrows(MemberException.class, () -> signUpApplication.signUp(form));
        verify(memberService, times(1)).isEmailExist(form.getEmail());
        verify(memberService, times(0)).createMember(form);
    }
}