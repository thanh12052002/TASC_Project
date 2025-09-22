package com.tasc.project.QLDT.model.grade;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
public class DauDiem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String moTa;
    private String tenDauDiem;

    @OneToMany(mappedBy = "dauDiem")
    private List<MonHocDauDiem> monHocDauDiems = new ArrayList<>();
}