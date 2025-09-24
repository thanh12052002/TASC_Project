package com.tasc.project.QLDT.business.dang_ky_tam.payload.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DangKyTamDto {
    private Long lopHocPhanId;
    private String tenMonHoc;
    private String tenLopHocPhan;
    private Integer soTinChi;
}
