package com.tasc.project.QLDT.model.academic;

import com.tasc.project.QLDT.model.student.SinhVienKhoa;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "khoa")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class Khoa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String ten;
    private String moTa;

    @OneToMany(mappedBy = "khoa")
    private List<BoMon> boMons = new ArrayList<>();

    @OneToMany(mappedBy = "khoa")
    private List<SinhVienKhoa> sinhVienKhoas = new ArrayList<>();
}
