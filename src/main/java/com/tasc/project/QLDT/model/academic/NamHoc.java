package com.tasc.project.QLDT.model.academic;

import jakarta.persistence.*;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@Getter
public class NamHoc {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String moTa;
    private String ten;

    @OneToMany(mappedBy = "namHoc")
    private List<KyHoc> kyHocs = new ArrayList<>();
}