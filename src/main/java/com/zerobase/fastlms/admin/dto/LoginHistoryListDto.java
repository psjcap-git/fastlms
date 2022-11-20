package com.zerobase.fastlms.admin.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LoginHistoryListDto {
    private long totalCount;
    private List<LoginHistoryDto> list;
}