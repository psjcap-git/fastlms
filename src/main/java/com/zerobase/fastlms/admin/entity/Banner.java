package com.zerobase.fastlms.admin.entity;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.zerobase.fastlms.admin.model.BannerInput;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Banner {
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)    
    private Long id;

    private String name;
    private String imageUrl;
    private String linkUrl;
    private String openType;
    private int sortValue;
    private boolean usingYn;
    private LocalDate regDt;

    public static Banner of(BannerInput parameter) {
        return Banner.builder()
                .name(parameter.getName())
                .imageUrl(parameter.getImageUrl())
                .linkUrl(parameter.getLinkUrl())
                .openType(parameter.getOpenType())
                .sortValue(parameter.getSortValue())
                .usingYn(parameter.isUsingYn())
                .regDt(parameter.getRegDt())
                .build();
    }
}
