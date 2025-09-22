package com.tasc.project.QLDT.model.academic;

import com.tasc.project.QLDT.model.grade.MonHocDauDiem;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "mon_hoc")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class MonHoc {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String ten;
    private String moTa;
    private Integer soTinChi;

    @ManyToOne
    @JoinColumn(name = "bo_mon_id")
    private BoMon boMon;

    @OneToMany(mappedBy = "monHoc")
    private List<MonHocKyHoc> monHocKyHocs = new ArrayList<>();

    @OneToMany(mappedBy = "monHoc")
    private List<MonHocDauDiem> monHocDauDiems = new ArrayList<>();

    @ManyToMany
    @JoinTable(
            name = "mon_tien_quyet",
            joinColumns = @JoinColumn(name = "mon_hoc_id"),
            inverseJoinColumns = @JoinColumn(name = "mon_tien_quyet_id")
    )
    private List<MonHoc> monTienQuyets = new ArrayList<>();

}
