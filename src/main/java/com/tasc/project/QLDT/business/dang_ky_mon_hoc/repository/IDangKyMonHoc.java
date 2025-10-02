package com.tasc.project.QLDT.business.dang_ky_mon_hoc.repository;

import com.tasc.project.QLDT.business.dang_ky_mon_hoc.payload.response.MonChuaDatDTO;
import com.tasc.project.QLDT.model.student.DangKyMonHoc;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IDangKyMonHoc extends JpaRepository<DangKyMonHoc,Long> {
    @Query(value = "CALL check_ket_qua_mon_dang_ky(:p_sinh_vien_khoa_id)", nativeQuery = true)
    List<MonChuaDatDTO> checkKetQua(@Param("p_sinh_vien_khoa_id") Long sinhVienKhoaId);
}
