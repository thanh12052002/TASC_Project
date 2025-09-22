package com.tasc.project.QLDT.model.student;

import com.tasc.project.QLDT.model.grade.MonHocDauDiem;
import jakarta.persistence.*;

@Entity
public class KetQua {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double diem;

    @ManyToOne
    @JoinColumn(name = "dang_ky_mon_hoc_id")
    private DangKyMonHoc dangKyMonHoc;

    @ManyToOne
    @JoinColumn(name = "mon_hoc_dau_diem_id")
    private MonHocDauDiem monHocDauDiem;
}