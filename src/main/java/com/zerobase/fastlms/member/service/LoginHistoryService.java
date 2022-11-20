package com.zerobase.fastlms.member.service;

import com.zerobase.fastlms.admin.dto.LoginHistoryListDto;

public interface LoginHistoryService {
    boolean addLoginHistory(String userId, String userAgent, String ipAddress);
    LoginHistoryListDto findAllLoginHistoryByUserId(String userId, long pageIndex, long pageSize);
}
