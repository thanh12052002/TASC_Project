package com.tasc.project.QLDT.model.academic;

import com.tasc.project.QLDT.common.constants.KyHocStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class KyHoc {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private KyHocStatus trangThai;

    @ManyToOne
    @JoinColumn(name = "hoc_ky_id")
    private HocKy hocKy;

    @ManyToOne
    @JoinColumn(name = "nam_hoc_id")
    private NamHoc namHoc;

    @OneToMany(mappedBy = "kyHoc")
    private List<MonHocKyHoc> monHocKyHocs = new ArrayList<>();
}