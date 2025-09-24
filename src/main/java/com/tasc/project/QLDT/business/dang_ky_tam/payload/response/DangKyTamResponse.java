package com.tasc.project.QLDT.business.dang_ky_tam.payload.response;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class DangKyTamResponse {
    private boolean status;
    private String message;
    private DangKyTamDto thongTinDangKy;
}
