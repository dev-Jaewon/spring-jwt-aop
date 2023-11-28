package com.devJaewon.springSecurity.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.devJaewon.springSecurity.exceptions.UnAuthorizedException;
import com.devJaewon.springSecurity.security.JwtProvider;

import jakarta.servlet.http.HttpServletRequest;

@Aspect
public class CheckAuthAop {

    @Around("@annotation(CheckAuth)")
    public Object checkAuthentication(ProceedingJoinPoint joinPoint) throws Throwable, UnAuthorizedException {

        String authorization = httpServletRequest().getHeader("Authorization");

        if (authorization == null || !authorization.contains("Bearer")) {
            throw new UnAuthorizedException();
        }

        try {
            String token = authorization.substring(7);
            JwtProvider jwtProvider = new JwtProvider(token);

            String userName = jwtProvider.getUserName();

            System.out.println(userName + "로그인 되었습니다.");

            return joinPoint.proceed();
        } catch (Exception e) {
            throw new UnAuthorizedException();
        }

    }

    private HttpServletRequest httpServletRequest() {
        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
                .getRequest();
    }

}
