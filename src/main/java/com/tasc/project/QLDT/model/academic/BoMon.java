package com.tasc.project.QLDT.model.academic;

import com.tasc.project.QLDT.model.schedule.GiangVien;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "bo_mon")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class BoMon {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "ten")
    private String ten;

    @Column(name = "mo_ta")
    private String moTa;

    @ManyToOne
    @JoinColumn(name = "khoa_id")
    private Khoa khoa;

    @OneToMany(mappedBy = "boMon")
    private List<GiangVien> giangViens = new ArrayList<>();

    @OneToMany(mappedBy = "boMon")
    private List<MonHoc> monHocs = new ArrayList<>();
}