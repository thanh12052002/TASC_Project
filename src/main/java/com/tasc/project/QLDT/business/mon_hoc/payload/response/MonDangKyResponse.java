package com.tasc.project.QLDT.business.mon_hoc.payload.response;

import java.time.LocalDateTime;

public interface MonDangKyResponse {
    Long getMonHocId();

    Long getMonHocKyHocId();
    String getTenMonHoc();
    Integer getSoTinChi();
    Integer getDaDangKy();
    LocalDateTime getThoiGianDangKy();

}
