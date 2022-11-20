package com.zerobase.fastlms.admin.model;

import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class BannerInput {
    private String name;
    private String linkUrl;
    private String openType;
    private int sortValue;
    private boolean usingYn;
    private LocalDate regDt;

    private String imageUrl;
}
