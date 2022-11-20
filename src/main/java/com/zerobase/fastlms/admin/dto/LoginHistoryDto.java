package com.zerobase.fastlms.admin.dto;

import java.time.LocalDateTime;

import com.zerobase.fastlms.member.entity.LoginHistory;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LoginHistoryDto {
    private Long id;
    private String userId;
    private LocalDateTime loginTime;
    private String userAgent;
    private String ipAddress;    
    private Long seq;

    public static LoginHistoryDto of(LoginHistory loginHistory) {
        return LoginHistoryDto.builder()
                .id(loginHistory.getId())
                .userId(loginHistory.getUserId())
                .loginTime(loginHistory.getLoginTime())
                .userAgent(loginHistory.getUserAgent())
                .ipAddress(loginHistory.getIpAddress())
                .build();
    }
}
