package com.tasc.project.QLDT.model.student;

import com.tasc.project.QLDT.model.academic.Khoa;
import jakarta.persistence.*;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@Getter
public class SinhVienKhoa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "sinh_vien_id")
    private SinhVien sinhVien;

    @ManyToOne
    @JoinColumn(name = "khoa_id")
    private Khoa khoa;

    @OneToMany(mappedBy = "sinhVienKhoa")
    private List<DangKyMonHoc> dangKyMonHocs = new ArrayList<>();
}