package com.tasc.project.QLDT.business.lop_hoc_phan.repository;

import com.tasc.project.QLDT.model.schedule.LopHocPhan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ILopHocPhanRepository extends JpaRepository<LopHocPhan,Long> {
    @Query("SELECT lhp FROM LopHocPhan lhp LEFT JOIN FETCH lhp.lichHocs WHERE lhp.monHocKyHoc.id = :monHocKyHocId")
    List<LopHocPhan> getLopHocPhan(@Param("monHocKyHocId") Long monHocKyHocId);
    //hoac danh Transaction o method service
}
