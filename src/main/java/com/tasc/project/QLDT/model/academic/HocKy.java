package com.tasc.project.QLDT.model.academic;

import jakarta.persistence.*;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
public class HocKy {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "mo_ta")
    private String moTa;

    @Column(name = "ten")
    private String ten;

    @OneToMany(mappedBy = "hocKy")
    private List<KyHoc> kyHocs = new ArrayList<>();
}