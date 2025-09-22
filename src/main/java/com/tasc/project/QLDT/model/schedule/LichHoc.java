package com.tasc.project.QLDT.model.schedule;

import jakarta.persistence.*;
import lombok.Getter;
import org.hibernate.annotations.Fetch;

import java.time.LocalDateTime;

@Entity
@Getter
public class LichHoc {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String moTa;
    private String ten;

    private Integer kip;
    private Integer thu;
    private Integer tuan;


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "giang_vien_id")
    private GiangVien giangVien;

    @ManyToOne
    @JoinColumn(name = "lop_hoc_phan_id")
    private LopHocPhan lopHocPhan;


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "phong_hoc_id")
    private PhongHoc phongHoc;
}