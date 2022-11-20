package com.zerobase.fastlms.admin.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.zerobase.fastlms.admin.entity.Banner;

@Repository
public interface BannerRepository extends JpaRepository<Banner, Long> {
}
