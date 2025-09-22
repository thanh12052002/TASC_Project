package com.tasc.project.QLDT.model.schedule;

import com.tasc.project.QLDT.model.academic.MonHocKyHoc;
import com.tasc.project.QLDT.model.student.DangKyMonHoc;
import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@Getter
public class LopHocPhan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String moTa;
    private String ten;

    @Column(name = "si_so_toi_da")
    private Integer siSoToiDa;

    @Column(name = "si_so_da_dang_ky")
    private Integer siSoDaDangKy;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "end_date")
    private LocalDateTime endDate;
    @ManyToOne
    @JoinColumn(name = "mh_kh_id")
    private MonHocKyHoc monHocKyHoc;

    @OneToMany(mappedBy = "lopHocPhan")
    private List<DangKyMonHoc> dangKyMonHocs = new ArrayList<>();

    @OneToMany(mappedBy = "lopHocPhan")
    private List<LichHoc> lichHocs = new ArrayList<>();
}