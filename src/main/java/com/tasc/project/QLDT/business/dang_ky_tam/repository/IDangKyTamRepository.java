package com.tasc.project.QLDT.business.dang_ky_tam.repository;

import com.tasc.project.QLDT.model.schedule.LopHocPhan;
import com.tasc.project.QLDT.model.student.DangKyMonTam;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface IDangKyTamRepository extends JpaRepository<DangKyMonTam,Long> {

    @Query(value = """
            SELECT 
                CASE WHEN COUNT(*) > 0 THEN true
                        ELSE false END              
            FROM dang_ky_mon_tam dkt
            WHERE dkt.lop_hoc_phan_id = :lopHocPhanId
                AND dkt.sinh_vien_khoa_id = :sinhVienKhoaId
         """,nativeQuery = true)
    boolean existsLopHocPhan(Long sinhVienKhoaId, Long lopHocPhanId);


    @Query("SELECT dkt FROM DangKyMonTam dkt LEFT JOIN dkt.lopHocPhan lhp " +
            "WHERE dkt.sinhVienKhoa.id = :sinhVienKhoaId " +
            "AND lhp.id = :lopHocPhanId")
    Optional<DangKyMonTam> getDangKyTam(@Param("sinhVienKhoaId") Long sinhVienKhoaId,
                                        @Param("lopHocPhanId") Long lopHocPhanId);
    @Query("SELECT dkt FROM DangKyMonTam dkt " +
            "JOIN FETCH dkt.lopHocPhan lhp " +
            "JOIN FETCH lhp.monHocKyHoc " +
            "WHERE dkt.sinhVienKhoa.id = :sinhVienKhoaId ")
    List<DangKyMonTam> findAllDangKyTam(@Param("sinhVienKhoaId") Long sinhVienKhoaId);
}
