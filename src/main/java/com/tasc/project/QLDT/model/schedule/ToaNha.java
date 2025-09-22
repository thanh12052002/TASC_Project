package com.tasc.project.QLDT.model.schedule;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
public class ToaNha {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String moTa;
    private String ten;

    @OneToMany(mappedBy = "toaNha")
    private List<PhongHoc> phongHocs = new ArrayList<>();
}