package com.tasc.project.QLDT.business.dang_ky_mon_hoc;

import com.tasc.project.QLDT.business.dang_ky_mon_hoc.payload.request.DangKyRequest;
import com.tasc.project.QLDT.business.dang_ky_mon_hoc.payload.response.DangKyResponse;
import com.tasc.project.QLDT.business.dang_ky_mon_hoc.service.DangKyMonHocService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/dang-ky-mon")
public class DangKyMonHocController {
    private final DangKyMonHocService dangKyMonHocService;

    @PostMapping("/xac-nhan")
    public ResponseEntity<DangKyResponse> dangKyMonHoc(
            @RequestBody DangKyRequest dangKyRequest
            ){
        Long sinhVienKhoaId = dangKyRequest.getSinhVienKhoaId();
        DangKyResponse dangKyResponse = dangKyMonHocService.confirmDangKy(sinhVienKhoaId);
        return ResponseEntity.ok().body(dangKyResponse);
    }
}
