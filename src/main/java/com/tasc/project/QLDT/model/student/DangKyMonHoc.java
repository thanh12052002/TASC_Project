package com.tasc.project.QLDT.model.student;

import com.tasc.project.QLDT.model.schedule.LopHocPhan;
import jakarta.persistence.*;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Setter
@Table(name = "dang_ky_mon_hoc")
public class DangKyMonHoc {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Column(name = "ghi_chu")
    private String ghiChu;

    @ManyToOne
    @JoinColumn(name = "lop_hoc_phan_id")
    private LopHocPhan lopHocPhan;

    @ManyToOne
    @JoinColumn(name = "sinh_vien_khoa_id")
    private SinhVienKhoa sinhVienKhoa;
}