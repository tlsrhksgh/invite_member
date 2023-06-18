//package com.zero.userapi.config.filter;
//
//import com.zero.config.JwtAuthenticationProvider;
//import com.zero.userapi.service.MemberService;
//import jakarta.servlet.*;
//import jakarta.servlet.annotation.WebFilter;
//import jakarta.servlet.http.HttpServletRequest;
//import lombok.RequiredArgsConstructor;
//
//import java.io.IOException;
//
//@RequiredArgsConstructor
//@WebFilter(urlPatterns = "/member/*")
//public class MemberFilter implements Filter {
//    private final JwtAuthenticationProvider jwtProvider;
//    private final MemberService memberService;
//
//    @Override
//    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
//            throws IOException, ServletException {
//        String path = ((HttpServletRequest) request).getServletPath().substring(8);
//
//        if(!path.equals("signin") && !path.equals("signup")) {
//            HttpServletRequest req = (HttpServletRequest) request;
//            String token = req.getHeader("Authentication");
//            if(!jwtProvider.isValidToken(token)) {
//                throw new ServletException("유효하지 않은 토큰입니다.");
//            }
//            String userEmail = jwtProvider.getUserEmail(token);
//
//            if(!memberService.isEmailExist(userEmail)) {
//                throw new ServletException("가입되지 않은 회원입니다.");
//            }
//        }
//
//        chain.doFilter(request, response);
//    }
//}
