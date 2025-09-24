package com.tasc.project.QLDT.model.academic;

import com.tasc.project.QLDT.model.schedule.LopHocPhan;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
public class MonHocKyHoc {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "ky_hoc_id")
    private KyHoc kyHoc;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "mon_hoc_id")
    private MonHoc monHoc;

    @OneToMany(mappedBy = "monHocKyHoc")
    private List<LopHocPhan> lopHocPhans = new ArrayList<>();
}