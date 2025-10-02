package com.tasc.project.QLDT.business.dang_ky_mon_hoc.payload.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MonChuaDatDTO {
    private final Long monHocId;
    private final String tenMonHoc;
    private final String danhSachTienQuyetChuaDat;

    public MonChuaDatDTO(Long monHocId, String tenMonHoc, String danhSachTienQuyetChuaDat) {
        this.monHocId = monHocId;
        this.tenMonHoc = tenMonHoc;
        this.danhSachTienQuyetChuaDat = danhSachTienQuyetChuaDat;
    }
    //

}
