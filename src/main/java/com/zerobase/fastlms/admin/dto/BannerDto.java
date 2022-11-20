package com.zerobase.fastlms.admin.dto;

import java.time.LocalDate;

import com.zerobase.fastlms.admin.entity.Banner;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BannerDto {
    private Long id;    
    private String name;
    private String imageUrl;
    private String linkUrl;
    private String openType;
    private int sortValue;
    private boolean usingYn;
    private LocalDate regDt;
    private Long seq;

    public static BannerDto of(Banner banner) {
        return BannerDto.builder()
                .id(banner.getId())
                .name(banner.getName())
                .imageUrl(banner.getImageUrl())
                .linkUrl(banner.getLinkUrl())
                .openType(banner.getOpenType())
                .sortValue(banner.getSortValue())
                .usingYn(banner.isUsingYn())
                .regDt(banner.getRegDt())
                .build();
    }
}
