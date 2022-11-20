package com.zerobase.fastlms.admin.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.zerobase.fastlms.admin.dto.BannerDto;
import com.zerobase.fastlms.admin.dto.BannerListDto;
import com.zerobase.fastlms.admin.entity.Banner;
import com.zerobase.fastlms.admin.mapper.BannerMapper;
import com.zerobase.fastlms.admin.model.BannerInput;
import com.zerobase.fastlms.admin.repository.BannerRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BannerServiceImpl implements BannerService {
    private final BannerRepository bannerRepository;
    private final BannerMapper bannerMapper;

    @Override
    public BannerListDto list(long pageIndex, long pageSize) {
        long totalCount = bannerRepository.count();

        List<BannerDto> bannerList = null;
        if(totalCount > 0) {
            bannerList = bannerMapper.select((pageIndex - 1) * pageSize, pageSize);        
            for(int ii = 0; ii < bannerList.size(); ++ii) {
                BannerDto dto = bannerList.get(ii);
                dto.setSeq(totalCount - ((pageIndex - 1) * pageSize + ii));
            }
        } else {
            bannerList = new ArrayList<>();
        }

        return new BannerListDto(totalCount, bannerList);        
    }

    @Override
    public boolean add(BannerInput parameter) {
        return bannerRepository.save(Banner.of(parameter)) != null;
    }

    @Override
    @Transactional
    public boolean delete(String idList) {
        if(idList == null || idList.length() == 0) {
            return true;
        }

        String[] ids = idList.split(",");
        for(int ii = 0; ii < ids.length; ++ii) {
            long id = Long.parseLong(ids[ii]);
            bannerRepository.deleteById(id);
        }
        return true;
    }

    @Override
    public List<BannerDto> bannerFormat() {
        return bannerMapper.selectBannerFormat();
    }    
}