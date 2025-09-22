package com.tasc.project.QLDT.business.giang_vien.repository;

import com.tasc.project.QLDT.model.auth.User;
import com.tasc.project.QLDT.model.schedule.GiangVien;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IGiangVienRepository extends JpaRepository<GiangVien,Long> {
    Optional<GiangVien> findByUser(User user);
}
