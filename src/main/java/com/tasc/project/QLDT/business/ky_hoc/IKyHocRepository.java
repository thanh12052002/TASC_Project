package com.tasc.project.QLDT.business.ky_hoc;

import com.tasc.project.QLDT.common.constants.KyHocStatus;
import com.tasc.project.QLDT.model.academic.KyHoc;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IKyHocRepository extends JpaRepository<KyHoc,Long> {
    @Query("SELECT k FROM KyHoc k WHERE k.trangThai = :status")
    public List<KyHoc> findByStatus(@Param("status")KyHocStatus status);
}
