package com.tasc.project.QLDT.business.sinh_vien.repository;

import com.tasc.project.QLDT.model.auth.User;
import com.tasc.project.QLDT.model.student.SinhVien;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ISinhVienRepository extends JpaRepository<SinhVien,Long> {
    Optional<SinhVien> findByUser(User user);
}
