package com.zerobase.fastlms.configuration;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

import com.zerobase.fastlms.member.service.LoginHistoryService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UserAuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {
    private final LoginHistoryService loginHistoryService;
    
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
            Authentication authentication) throws IOException, ServletException {      
                
        UserDetails userDetails = (UserDetails)authentication.getPrincipal();
        String userId = userDetails.getUsername();

        String userAgent = request.getHeader("User-Agent");
        String ipAddress = request.getRemoteAddr();

        loginHistoryService.addLoginHistory(userId, userAgent, ipAddress);

        super.onAuthenticationSuccess(request, response, authentication);
    }
}
