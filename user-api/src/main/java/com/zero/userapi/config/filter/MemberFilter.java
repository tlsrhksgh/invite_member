package com.zero.userapi.config.filter;

import com.zero.config.JwtAuthenticationProvider;
import com.zero.userapi.exception.MemberException;
import com.zero.userapi.service.MemberService;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

import java.io.IOException;

import static com.zero.userapi.exception.ErrorCode.*;

@RequiredArgsConstructor
@WebFilter(urlPatterns = "/member/*")
public class MemberFilter implements Filter {
    private final JwtAuthenticationProvider jwtProvider;
    private final MemberService memberService;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        String token = req.getHeader("X_AUTH_TOKEN");
        if(!jwtProvider.isValidToken(token)) {
            throw new ServletException("유효하지 않은 토큰입니다.");
        }
        String userEmail = jwtProvider.getUserEmail(token);
        System.out.println(userEmail);

        if(!memberService.isEmailExist(userEmail)) {
            throw new MemberException(MEMBER_NOT_FOUND);
        }

        chain.doFilter(request, response);
    }
}
