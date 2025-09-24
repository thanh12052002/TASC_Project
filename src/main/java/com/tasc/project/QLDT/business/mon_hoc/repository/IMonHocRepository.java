package com.tasc.project.QLDT.business.mon_hoc.repository;

import com.tasc.project.QLDT.business.mon_hoc.payload.response.MonDangKyResponse;
import com.tasc.project.QLDT.model.academic.MonHoc;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IMonHocRepository extends JpaRepository<MonHoc,Long> {
    @Query(value = """
        WITH mon_hoc_mo AS (
            SELECT 
                mh.id AS mon_hoc_id,
                mh.ten AS ten_mon_hoc,
                mh.so_tin_chi,
                mhkh.id AS mhkh_id,
                svk.id AS svk_id
            FROM sinh_vien sv
            JOIN sinh_vien_khoa svk ON svk.sinh_vien_id = sv.id
            JOIN khoa k ON svk.khoa_id = k.id
            JOIN bo_mon bm ON bm.khoa_id = k.id
            JOIN mon_hoc mh ON mh.bo_mon_id = bm.id
            JOIN mon_hoc_ky_hoc mhkh ON mhkh.mon_hoc_id = mh.id
            JOIN ky_hoc kh ON kh.id = mhkh.ky_hoc_id
            WHERE sv.id = :sinhVienId
                  AND k.id = :khoaId
                  AND kh.id = :kyHocId
        )
        SELECT
            mhm.svk_id as sinh_vien_khoa_id,
            mhm.mon_hoc_id,
            mhm.ten_mon_hoc,
            mhm.so_tin_chi,
            mhm.mhkh_id as mon_hoc_ky_hoc_id,
            (EXISTS (
                SELECT 1
                FROM lop_hoc_phan lhp
                JOIN dang_ky_mon_hoc dkmh
                    ON dkmh.lop_hoc_phan_id = lhp.id
                WHERE lhp.mh_kh_id = mhm.mhkh_id
                  AND dkmh.sinh_vien_khoa_id = mhm.svk_id
            )) AS da_dang_ky,
            (
                SELECT dkmh.created_at
                FROM lop_hoc_phan lhp
                JOIN dang_ky_mon_hoc dkmh
                    ON dkmh.lop_hoc_phan_id = lhp.id
                WHERE lhp.mh_kh_id = mhm.mhkh_id
                  AND dkmh.sinh_vien_khoa_id = mhm.svk_id
                ORDER BY dkmh.created_at DESC
                LIMIT 1
            ) AS thoi_gian_dang_ky
        FROM mon_hoc_mo mhm
    """, nativeQuery = true)
    List<MonDangKyResponse> getInfoDangKy(Long sinhVienId, Long khoaId, Long kyHocId);

    @Query("SELECT mh FROM LopHocPhan lhp JOIN lhp.monHocKyHoc mhkh " +
            "JOIN mhkh.monHoc mh WHERE lhp.id = :lopHocPhanId")
    MonHoc getMonHocByLhp(@Param("lopHocPhanId") Long lopHocPhanId);
}
