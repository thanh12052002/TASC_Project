package com.tasc.project.QLDT.model.grade;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "diem_chu")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DiemChu {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "diem_chu", length = 255)
    private String diemChu;

    @Column(name = "diem_tu")
    private Double diemTu;

    @Column(name = "diem_den")
    private Double diemDen;

    @Column(name = "gpa")
    private Double gpa;
}