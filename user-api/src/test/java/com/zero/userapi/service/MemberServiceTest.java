package com.zero.userapi.service;

import com.zero.exception.MemberException;
import com.zero.model.Member;
import com.zero.model.dto.SignUpForm;
import com.zero.userapi.domain.member.SignInForm;
import com.zero.userapi.domain.repository.MemberRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static com.zero.exception.ErrorCode.INVALID_PASSWORD;
import static com.zero.exception.ErrorCode.MEMBER_NOT_FOUND;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class MemberServiceTest {

    @Mock
    private MemberRepository memberRepository;

    @InjectMocks
    private MemberService memberService;

    @Test
    @DisplayName("SignUpForm에서 검증된 회원만 가입 성공")
    public void createMember_ValidForm_SaveMember() {
        // given
        SignUpForm form = SignUpForm.builder()
                .email("test@naver.com")
                .name("test")
                .password("1234")
                .phoneNumber("01012341234")
                .build();

        // when
        memberService.createMember(form);

        // then
        verify(memberRepository).save(any(Member.class));
    }

    @Test
    @DisplayName("이미 가입된 이메일 주소라면 true를 반환")
    public void isEmailExist_AlreadyRegisterEmail_ReturnTrue() {
        // given
        Member member = Member.builder()
                .email("test@naver.com")
                .name("test")
                .password("1234")
                .phoneNumber("01012341234")
                .build();
        given(memberRepository.findByEmail(member.getEmail()))
                .willReturn(Optional.of(member));

        // when
        boolean result = memberService.isEmailExist(member.getEmail());

        // then
        assertTrue(result);
    }

    @Test
    @DisplayName("가입이 되지 않은 이메일 주소라면 false를 반환")
    public void isEmailExist_NonRegisterEmail_ReturnFalse() {
        // given
        String email = "test@naver.com";
        given(memberRepository.findByEmail(email))
                .willReturn(Optional.empty());

        // when
        boolean result = memberService.isEmailExist(email);

        // then
        assertFalse(result);
    }

    @Test
    @DisplayName("가입되어 있는 회원이라면 Member를 반환")
    public void findValidMember_AlreadyRegisterMember_ReturnMember() {
        // given
        SignInForm form = new SignInForm("test@naver.com", "1234");

        Member member = Member.builder()
                .email(form.getEmail())
                .name("test")
                .password(form.getPassword())
                .phoneNumber("01012341234")
                .build();

        given(memberRepository.findByEmail(form.getEmail()))
                .willReturn(Optional.of(member));

        // when
        Member result = memberService.findValidMember(form);

        // then
        assertEquals(member.getEmail(), result.getEmail());
        assertEquals(member.getPassword(), result.getPassword());
        assertEquals(member.getName(), result.getName());
        assertEquals(member.getPhoneNumber(), result.getPhoneNumber());
        verify(memberRepository, times(1)).findByEmail(form.getEmail());
    }

    @Test
    @DisplayName("가입되어 있지 않은 회원이라면 Member Exception이 발생")
    public void findValidMember_UnregisteredMember_ThrowException() {
        // given
        SignInForm form = new SignInForm("test@naver.com", "1234");

        given(memberRepository.findByEmail(form.getEmail()))
                .willReturn(Optional.empty());

        // when
        MemberException exception = assertThrows(MemberException.class, () -> memberService.findValidMember(form));

        // then
        assertEquals(MEMBER_NOT_FOUND, exception.getErrorCode());
        verify(memberRepository, times(1)).findByEmail(form.getEmail());
    }

    @Test
    @DisplayName("패스워드가 일치하지 않다면 Member Exception이 발생")
    public void findValidMember_InvalidPassword_ThrowException() {
        // given
        SignInForm form = new SignInForm("test@naver.com", "1234");

        Member member = Member.builder()
                .email(form.getEmail())
                .name("test")
                .password("5678")
                .phoneNumber("01012341234")
                .build();

        given(memberRepository.findByEmail(form.getEmail()))
                .willReturn(Optional.of(member));

        // when
        MemberException exception = assertThrows(MemberException.class, () -> memberService.findValidMember(form));

        // then
        assertEquals(INVALID_PASSWORD, exception.getErrorCode());
        verify(memberRepository, times(1)).findByEmail(form.getEmail());
    }
}