package com.tasc.project.QLDT.business.sinh_vien_khoa;

import com.tasc.project.QLDT.model.academic.Khoa;
import com.tasc.project.QLDT.model.student.SinhVienKhoa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ISinhVienKhoaRepository extends JpaRepository<SinhVienKhoa,Long> {
}
