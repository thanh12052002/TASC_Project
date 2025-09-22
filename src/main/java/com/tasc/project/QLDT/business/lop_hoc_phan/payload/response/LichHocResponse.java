package com.tasc.project.QLDT.business.lop_hoc_phan.payload.response;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class LichHocResponse {
    private List<Integer> kips;
    private String tenGiangVien;
    private String tenPhongHoc;
}
