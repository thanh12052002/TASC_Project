package com.tasc.project.QLDT.business.lop_hoc_phan;

import com.tasc.project.QLDT.business.lop_hoc_phan.payload.response.LopHocPhanResponse;
import com.tasc.project.QLDT.business.lop_hoc_phan.service.LopHocPhanService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/lop-hoc-phan")
@RequiredArgsConstructor
public class controller {
    private final LopHocPhanService lopHocPhanService;
    @GetMapping("/dang-ky")
    public ResponseEntity<List<LopHocPhanResponse>> getInfoLopHocPhan(
            @RequestParam("monHocKyHocId") Long monHocKyHocId
    ){
        List<LopHocPhanResponse> lopHocPhanResponses = lopHocPhanService.getInfoLopHocPhan(monHocKyHocId);
        return ResponseEntity.ok().body(lopHocPhanResponses);
    }
}
