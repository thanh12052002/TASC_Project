package com.tasc.project.QLDT.business.sinh_vien.controller;

import com.tasc.project.QLDT.business.mon_hoc.payload.response.MonDangKyResponse;
import com.tasc.project.QLDT.business.sinh_vien.payload.response.KhoaAndKyHoc.KhoaAndKyHocResponse;
import com.tasc.project.QLDT.business.sinh_vien.payload.response.KhoaAndKyHoc.KyHocResponse;
import com.tasc.project.QLDT.business.sinh_vien.payload.response.KhoaAndKyHoc.SinhVienKhoaResponse;
import com.tasc.project.QLDT.business.sinh_vien.service.SinhVienService;
import com.tasc.project.QLDT.model.student.SinhVienKhoa;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/sinh-vien/information")
public class SinhVienController {
    private final SinhVienService sinhVienService;
    @GetMapping("/khoa-kyhoc")
    public ResponseEntity<KhoaAndKyHocResponse> getKhoaAndKyHoc(){
        KhoaAndKyHocResponse khoaAndKyHocResponse = sinhVienService.getInfomation();
        return ResponseEntity.ok(khoaAndKyHocResponse);
    }

    @GetMapping("/mon-hoc/dang-ky")
    public ResponseEntity<List<MonDangKyResponse>> getInfoMonDangKy(
            @RequestParam("khoaId") Long khoaId,
            @RequestParam("kyHocId") Long kyHocId
    ){
        List<MonDangKyResponse> monDangKyResponses = sinhVienService.getInfoMonDangKy(khoaId,kyHocId);
        return ResponseEntity.ok().body(monDangKyResponses);
    }

    @GetMapping("/sinh-vien-khoa")
    public ResponseEntity<SinhVienKhoaResponse> getInfoSinhVienKhoa(
            @RequestParam("khoaId") Long khoaId
    ){
        SinhVienKhoa svk = sinhVienService.getInfoSinhVienKhoa(khoaId);
        SinhVienKhoaResponse response = new SinhVienKhoaResponse();
        response.setId(svk.getId());
        return ResponseEntity.ok().body(response);
    }
}
