package com.tasc.project.QLDT.model.student;

import com.tasc.project.QLDT.common.constants.DangKyTamStatus;
import com.tasc.project.QLDT.model.schedule.LopHocPhan;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "dang_ky_mon_tam")
public class DangKyMonTam {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sinh_vien_khoa_id", nullable = false)
    private SinhVienKhoa sinhVienKhoa;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "lop_hoc_phan_id",nullable = false)
    private LopHocPhan lopHocPhan;

    // Ngày chọn@Column(name = "ngay_chon")
    private LocalDateTime ngayChon;

    // Trạng thái (tạm, locked, expired,...)
    @Enumerated(EnumType.STRING)
    private DangKyTamStatus trangThai;

}
