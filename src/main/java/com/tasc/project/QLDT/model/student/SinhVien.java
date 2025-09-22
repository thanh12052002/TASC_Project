package com.tasc.project.QLDT.model.student;

import com.tasc.project.QLDT.model.auth.User;
import jakarta.persistence.*;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@Getter
public class SinhVien {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id", unique = true)
    private User user;

    @OneToMany(mappedBy = "sinhVien")
    private List<SinhVienKhoa> sinhVienKhoas = new ArrayList<>();
}
