package com.imorate.springsystem.configuration;

import org.springframework.security.authentication.*;
import org.springframework.security.web.authentication.rememberme.RememberMeAuthenticationException;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
public class AuthExceptionHandler {
    private final HttpServletRequest httpServletRequest;

    public AuthExceptionHandler(HttpServletRequest httpServletRequest) {
        this.httpServletRequest = httpServletRequest;
    }

    public int getAuthErrorMessage() {
        Exception exception = (Exception) httpServletRequest.getSession().getAttribute("SPRING_SECURITY_LAST_EXCEPTION");
        if (exception instanceof BadCredentialsException) {
            return 0;
        } else if (exception instanceof LockedException) {
            return 1;
        } else if (exception instanceof RememberMeAuthenticationException) {
            return 2;
        } else if (exception instanceof AccountExpiredException) {
            return 3;
        } else if (exception instanceof CredentialsExpiredException) {
            return 4;
        } else if (exception instanceof AccountStatusException) {
            return 5;
        } else {
            return -1;
        }
    }
}
