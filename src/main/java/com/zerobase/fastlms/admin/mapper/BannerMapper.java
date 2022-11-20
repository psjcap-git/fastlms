package com.zerobase.fastlms.admin.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.transaction.annotation.Transactional;

import com.zerobase.fastlms.admin.dto.BannerDto;

@Mapper
@Transactional
public interface BannerMapper {        
    List<BannerDto> select(long pageStart, long pageSize);
    List<BannerDto> selectBannerFormat();
}
