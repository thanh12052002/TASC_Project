package com.tasc.project.QLDT.business.lop_hoc_phan.payload.response;

import jakarta.persistence.Column;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
@Setter
public class LopHocPhanResponse {
    private Long id;
    private String ten;
    private String moTa;
    private Integer siSoToiDa;
    private Integer siSoDangKy;
    private LocalDateTime createdAt;
    private LocalDateTime endDate;
    private Map<Integer, Map<Integer, LichHocResponse>> thoiGianLichHoc;
}
