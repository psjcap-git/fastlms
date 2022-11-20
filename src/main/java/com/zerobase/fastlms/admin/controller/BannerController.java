package com.zerobase.fastlms.admin.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.zerobase.fastlms.admin.dto.BannerListDto;
import com.zerobase.fastlms.admin.model.BannerInput;
import com.zerobase.fastlms.admin.service.BannerService;
import com.zerobase.fastlms.course.controller.BaseController;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class BannerController extends BaseController {
    private final BannerService bannerService;
    
    @GetMapping("/admin/banner/list.do")
    public String list(Model model, @RequestParam(name = "pageIndex", defaultValue = "1") long pageIndex) {

        long pageSize = 10;

        BannerListDto bannerListDto = bannerService.list(pageIndex, pageSize);
        model.addAttribute("bannerList", bannerListDto);

        String queryString = "";
        String pagerHtml = getPaperHtml(bannerListDto.getTotalCount(), pageSize, pageIndex, queryString);
        model.addAttribute("pager", pagerHtml);

        return "admin/banner/list.html";
    }
    
    @GetMapping("/admin/banner/add.do")
    public String add() {
        return "admin/banner/add.html";
    }

    private String[] getNewSaveFile(String baseLocalPath, String baseUrlPath, String originalFilename) {
    
        LocalDate now = LocalDate.now();
    
        String[] dirs = {
                String.format("%s/%d/", baseLocalPath,now.getYear()),
                String.format("%s/%d/%02d/", baseLocalPath, now.getYear(),now.getMonthValue()),
                String.format("%s/%d/%02d/%02d/", baseLocalPath, now.getYear(), now.getMonthValue(), now.getDayOfMonth())};
        
        String urlDir = String.format("%s/%d/%02d/%02d/", baseUrlPath, now.getYear(), now.getMonthValue(), now.getDayOfMonth());
        
        for(String dir : dirs) {
            File file = new File(dir);
            if (!file.isDirectory()) {
                file.mkdir();
            }
        }
        
        String fileExtension = "";
        if (originalFilename != null) {
            int dotPos = originalFilename.lastIndexOf(".");
            if (dotPos > -1) {
                fileExtension = originalFilename.substring(dotPos + 1);
            }
        }
        
        String uuid = UUID.randomUUID().toString().replaceAll("-", "");
        String newFilename = String.format("%s%s", dirs[2], uuid);
        String newUrlFilename = String.format("%s%s", urlDir, uuid);
        if (fileExtension.length() > 0) {
            newFilename += "." + fileExtension;
            newUrlFilename += "." + fileExtension;
        }
    
        return new String[]{newFilename, newUrlFilename};
    }
    
    @PostMapping(value = {"/admin/banner/add.do"})
    public String addSubmit(Model model, HttpServletRequest request, MultipartFile imageFile, BannerInput parameter) {
        String saveFilename = "";
        String urlFilename = "";        
        if (imageFile != null) {
            String originalFilename = imageFile.getOriginalFilename();
            
            String baseLocalPath = "C:/Work/HomeWork/fastlms3/files";
            String baseUrlPath = "/files";
            
            String[] arrFilename = getNewSaveFile(baseLocalPath, baseUrlPath, originalFilename);
    
            saveFilename = arrFilename[0];
            urlFilename = arrFilename[1];
            
            try {
                File newFile = new File(saveFilename);
                FileCopyUtils.copy(imageFile.getInputStream(), new FileOutputStream(newFile));
            } catch (IOException e) {
                System.out.println(e.toString());
            }
        }        
        parameter.setImageUrl(urlFilename);
        parameter.setRegDt(LocalDate.now());
        
        bannerService.add(parameter);
        return "redirect:/admin/banner/list.do";
    }

    @PostMapping("/admin/banner/delete.do")
    public String delete(@RequestParam(value = "idList") String idList) {
        bannerService.delete(idList);
        return "redirect:/admin/banner/list.do";
    }
}
