package com.tasc.project.QLDT.model.grade;

import com.tasc.project.QLDT.model.academic.MonHoc;
import jakarta.persistence.*;

@Entity
public class MonHocDauDiem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double tyLe;

    @ManyToOne
    @JoinColumn(name = "dau_diem_id")
    private DauDiem dauDiem;

    @ManyToOne
    @JoinColumn(name = "mon_hoc_id")
    private MonHoc monHoc;
}