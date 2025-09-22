package com.tasc.project.QLDT.model.schedule;


import jakarta.persistence.*;
import lombok.Getter;

import java.util.List;
import java.util.Set;
@Entity
@Getter
public class PhongHoc {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String moTa;
    private String ten;
    private Integer sucChua;

    @ManyToOne
    @JoinColumn(name = "toa_nha_id")
    private ToaNha toaNha;

    @OneToMany(mappedBy = "phongHoc")
    private List<LichHoc> lichHocs;
}