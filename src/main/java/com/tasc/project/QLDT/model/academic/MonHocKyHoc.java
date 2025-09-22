package com.tasc.project.QLDT.model.academic;

import com.tasc.project.QLDT.model.schedule.LopHocPhan;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
public class MonHocKyHoc {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "ky_hoc_id")
    private KyHoc kyHoc;

    @ManyToOne
    @JoinColumn(name = "mon_hoc_id")
    private MonHoc monHoc;

    @OneToMany(mappedBy = "monHocKyHoc")
    private List<LopHocPhan> lopHocPhans = new ArrayList<>();
}