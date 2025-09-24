package com.tasc.project.QLDT.business.dang_ky_tam.controller;

import com.tasc.project.QLDT.business.dang_ky_tam.payload.request.DangKyTamRequest;
import com.tasc.project.QLDT.business.dang_ky_tam.payload.response.DangKyTamResponse;
import com.tasc.project.QLDT.business.dang_ky_tam.service.DangKyTamService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/dang-ky-tam")
public class DangKyTamController {
    private final DangKyTamService dangKyTamService;

    @PostMapping("/them-moi")
    public ResponseEntity<DangKyTamResponse> addToDangKyTam(@RequestBody DangKyTamRequest request){
        DangKyTamResponse dangKyTamResponse  = dangKyTamService.checkInfoDangKyTam(request.getSinhVienKhoaId(),request.getLopHocPhanId());
        return ResponseEntity.ok().body(dangKyTamResponse);
    }
}
