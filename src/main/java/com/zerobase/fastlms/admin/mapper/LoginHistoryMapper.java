package com.zerobase.fastlms.admin.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.data.repository.query.Param;

import com.zerobase.fastlms.admin.dto.LoginHistoryDto;

@Mapper
public interface LoginHistoryMapper {
    long selectCountByUserId(
        @Param("userId") String userId);

    List<LoginHistoryDto> selectListByUserId(
        @Param("userId") String userId,
        @Param("pageStart") long pageStart,
        @Param("pageSize") long pageSize );
}