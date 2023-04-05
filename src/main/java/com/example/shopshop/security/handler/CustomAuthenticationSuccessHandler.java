package com.example.shopshop.security.handler;

import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.stereotype.Component;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Component
@Log4j2
public class CustomAuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    private RequestCache requestCache = new HttpSessionRequestCache();
    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

//    @Override
//    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
//
//        setDefaultTargetUrl("/");
//
//        SavedRequest savedRequest = requestCache.getRequest(request, response);
//
//        if (savedRequest != null) {
//            String targetUrl = savedRequest.getRedirectUrl();
//            redirectStrategy.sendRedirect(request, response, targetUrl);
//            log.info("!= null & url : " + targetUrl);
//        } else {
//            redirectStrategy.sendRedirect(request, response, getDefaultTargetUrl());
//            log.info("== null & url : ");
//        }
//    }


    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authentication) throws IOException, ServletException {

        clearSession(request);

        SavedRequest savedRequest = requestCache.getRequest(request, response);

        String prevPage = (String) request.getSession().getAttribute("prevPage");
        if (prevPage != null) {
            request.getSession().removeAttribute("prevPage");
        }

        String uri = "/";

        if (savedRequest != null) {
            uri = savedRequest.getRedirectUrl();
        } else if (prevPage != null && !prevPage.equals("")) {
            if (prevPage.contains("/member/login")) {
                uri = "/";
            } else {
                uri = prevPage;
            }
        }

        redirectStrategy.sendRedirect(request, response, uri);
    }

    private void clearSession(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
        }
    }
}

