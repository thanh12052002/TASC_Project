package com.tasc.project.QLDT.security.utils;

import com.tasc.project.QLDT.business.giang_vien.repository.IGiangVienRepository;
import com.tasc.project.QLDT.business.sinh_vien.repository.ISinhVienRepository;
import com.tasc.project.QLDT.business.user.repository.IUserRepository;
import com.tasc.project.QLDT.model.auth.User;
import com.tasc.project.QLDT.model.schedule.GiangVien;
import com.tasc.project.QLDT.model.student.SinhVien;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.nio.file.AccessDeniedException;

@Component
@RequiredArgsConstructor
public class SecurityUtils {
    private final IUserRepository userRepository;
    private final ISinhVienRepository sinhVienRepository;
    private final IGiangVienRepository giangVienRepository;

    public String getUserNameByContext(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()){
            throw new RuntimeException("User is not authenticated");
        }
        return authentication.getName();
    }
    //
    public User getCurrentUser(){
        String username = getUserNameByContext();
        return userRepository.findByUserName(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }
    //
    public SinhVien getCurrentSinhVien(){
        return sinhVienRepository.findByUser(getCurrentUser())
                .orElseThrow(() -> new UsernameNotFoundException("Not found Sinh Vien"));
    }
    //
    public GiangVien getCurrentGiangVien(){
        return giangVienRepository.findByUser(getCurrentUser())
                .orElseThrow(() -> new UsernameNotFoundException("Not found Giang Vien"));
    }
}
