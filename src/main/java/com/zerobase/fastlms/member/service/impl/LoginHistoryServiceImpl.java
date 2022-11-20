package com.zerobase.fastlms.member.service.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.zerobase.fastlms.admin.dto.LoginHistoryDto;
import com.zerobase.fastlms.admin.dto.LoginHistoryListDto;
import com.zerobase.fastlms.admin.mapper.LoginHistoryMapper;
import com.zerobase.fastlms.member.entity.LoginHistory;
import com.zerobase.fastlms.member.repository.LoginHistoryRepository;
import com.zerobase.fastlms.member.service.LoginHistoryService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LoginHistoryServiceImpl implements LoginHistoryService {
    private final LoginHistoryRepository loginHistoryRepository;
    private final LoginHistoryMapper loginHistoryMapper;

    @Override
    public boolean addLoginHistory(String userId, String userAgent, String ipAddress) {
        LoginHistory loginHistory = LoginHistory.builder()
            .userId(userId)
            .userAgent(userAgent)
            .ipAddress(ipAddress)
            .loginTime(LocalDateTime.now())
            .build();

        loginHistoryRepository.save(loginHistory);
        return true;
    }

    @Override
    public LoginHistoryListDto findAllLoginHistoryByUserId(String userId, long pageIndex, long pageSize) {        
        long totalCount = loginHistoryMapper.selectCountByUserId(userId);

        List<LoginHistoryDto> resultList = null;
        if(totalCount > 0) {
            resultList = loginHistoryMapper.selectListByUserId(userId, (pageIndex - 1) * pageSize, pageSize);
            for(int ii = 0; ii < resultList.size(); ++ii) {
                LoginHistoryDto dto = resultList.get(ii);
                dto.setSeq(totalCount - ((pageIndex - 1) * pageSize + ii));
            }
        } else {
            resultList = new ArrayList<LoginHistoryDto>();
        }

        return new LoginHistoryListDto(totalCount, resultList);
    }
}