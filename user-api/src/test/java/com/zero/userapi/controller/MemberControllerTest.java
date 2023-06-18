package com.zero.userapi.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zero.model.dto.SignUpForm;
import com.zero.userapi.application.SignInApplication;
import com.zero.userapi.application.SignUpApplication;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(MemberController.class)
class MemberControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private SignUpApplication signUpApplication;

    @MockBean
    private SignInApplication signInApplication;

    @Test
    void signUp_SuccessSignUp() throws Exception {
        //given
        SignUpForm form = SignUpForm.builder()
                .email("test@naver.com")
                .name("test")
                .password("adsfasdfasd")
                .phoneNumber("01012341234")
                .build();

        given(signUpApplication.signUp(any(SignUpForm.class)))
                .willReturn("회원 가입이 완료되었습니다.");

        //when
        //then
        mockMvc.perform(post("/member/signup")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(form)))
                .andExpect(status().isOk())
                .andExpect(content().string("회원 가입이 완료되었습니다."))
                .andDo(print());
    }

    @Test
    void signUp_NotBlankPhoneNumber_FailedSignUp() throws Exception {
        //given
        SignUpForm form = SignUpForm.builder()
                .email("test@naver.com")
                .name("test")
                .password("asdfasdf")
                .phoneNumber("")
                .build();

        given(signUpApplication.signUp(form))
                .willReturn("핸드폰 번호 입력은 필수입니다.");

        //when
        //then
        mockMvc.perform(post("/member/signup")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(form)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").value("핸드폰 번호 입력은 필수입니다."))
                .andDo(print());
    }

    @Test
    void signUp_NotBlankEmail_FailedSignUp() throws Exception {
        //given
        SignUpForm form = SignUpForm.builder()
                .email(" ")
                .name("test")
                .password("asdfasdf")
                .phoneNumber("01012341234")
                .build();

        given(signUpApplication.signUp(form))
                .willReturn("이메일 입력은 필수입니다.");

        //when
        //then
        mockMvc.perform(post("/member/signup")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(form)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").value("이메일 입력은 필수입니다."))
                .andDo(print());
    }

    @Test
    void signIn_ValidCredential() throws Exception {
        //given
        String token = "temptoken";

        given(signInApplication.signIn(any()))
                .willReturn(token);

        //when
        //then
        mockMvc.perform(post("/member/signin")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{ \"email\":  \"test@test.com\", \"password\":  \"test\" }"))
                .andExpect(status().isOk())
                .andExpect(content().string(token))
                .andDo(print());
    }

    @Test
    void signIn_InvalidCredential() throws Exception {
        //given
        given(signInApplication.signIn(any()))
                .willReturn("가입되지 않은 회원입니다.");

        //when
        //then
        mockMvc.perform(post("/member/signin")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{ \"email\":  \"test@test.com\", \"password\":  \"test\" }"))
                .andExpect(status().isOk())
                .andExpect(content().string("가입되지 않은 회원입니다."))
                .andDo(print());
    }
}