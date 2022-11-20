package com.zerobase.fastlms.admin.controller;


import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

//import com.zerobase.fastlms.admin.dto.LoginHistoryDto;
import com.zerobase.fastlms.admin.dto.LoginHistoryListDto;
import com.zerobase.fastlms.admin.dto.MemberDto;
import com.zerobase.fastlms.admin.model.MemberInput;
import com.zerobase.fastlms.admin.model.MemberParam;
import com.zerobase.fastlms.course.controller.BaseController;
import com.zerobase.fastlms.member.service.LoginHistoryService;
import com.zerobase.fastlms.member.service.MemberService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class AdminMemberController extends BaseController {
    
    private final MemberService memberService;
    private final LoginHistoryService loginHistoryService;
    
    @GetMapping("/admin/member/list.do")
    public String list(Model model, MemberParam parameter) {
        
        parameter.init();
        List<MemberDto> members = memberService.list(parameter);
        
        long totalCount = 0;
        if (members != null && members.size() > 0) {
            totalCount = members.get(0).getTotalCount();
        }
        String queryString = parameter.getQueryString();
        String pagerHtml = getPaperHtml(totalCount, parameter.getPageSize(), parameter.getPageIndex(), queryString);
        
        model.addAttribute("list", members);
        model.addAttribute("totalCount", totalCount);
        model.addAttribute("pager", pagerHtml);
        
        return "admin/member/list";
    }
    
    @GetMapping("/admin/member/detail.do")
    public String detail(Model model, MemberParam parameter) {        
        parameter.init();
        
        MemberDto member = memberService.detail(parameter.getUserId());
        model.addAttribute("member", member);

        LoginHistoryListDto loginHistoryListDto = loginHistoryService.findAllLoginHistoryByUserId(
            parameter.getUserId(), parameter.getPageIndex(), parameter.getPageSize());
        model.addAttribute("loginHistory", loginHistoryListDto);

        String queryString = "userId=" + parameter.getUserId();
        String pagerHtml = getPaperHtml(loginHistoryListDto.getTotalCount(), parameter.getPageSize(), parameter.getPageIndex(), queryString);
        model.addAttribute("pager", pagerHtml);
       
        return "admin/member/detail";
    }
    
    @PostMapping("/admin/member/status.do")
    public String status(Model model, MemberInput parameter) {        
        memberService.updateStatus(parameter.getUserId(), parameter.getUserStatus());        
        return "redirect:/admin/member/detail.do?userId=" + parameter.getUserId();
    }
    
    
    @PostMapping("/admin/member/password.do")
    public String password(Model model, MemberInput parameter) {        
        memberService.updatePassword(parameter.getUserId(), parameter.getPassword());        
        return "redirect:/admin/member/detail.do?userId=" + parameter.getUserId();
    }
}