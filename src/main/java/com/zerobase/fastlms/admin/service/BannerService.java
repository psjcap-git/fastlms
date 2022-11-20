package com.zerobase.fastlms.admin.service;

import java.util.List;

import com.zerobase.fastlms.admin.dto.BannerDto;
import com.zerobase.fastlms.admin.dto.BannerListDto;
import com.zerobase.fastlms.admin.model.BannerInput;

public interface BannerService {
    BannerListDto list(long pageStart, long pageSize);
    boolean add(BannerInput parameter);
    boolean delete(String idList);
    List<BannerDto> bannerFormat();
}
