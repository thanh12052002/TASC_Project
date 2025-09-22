package com.tasc.project.QLDT.business.sinh_vien.service;

import com.tasc.project.QLDT.business.ky_hoc.IKyHocRepository;
import com.tasc.project.QLDT.business.mon_hoc.payload.response.MonDangKyResponse;
import com.tasc.project.QLDT.business.mon_hoc.service.MonHocService;
import com.tasc.project.QLDT.business.sinh_vien.converter.KhoaToKhoaResponse;
import com.tasc.project.QLDT.business.sinh_vien.converter.KyHocToKyHocResponse;
import com.tasc.project.QLDT.business.sinh_vien.payload.response.KhoaAndKyHoc.KhoaAndKyHocResponse;
import com.tasc.project.QLDT.business.sinh_vien.payload.response.KhoaAndKyHoc.KhoaResponse;
import com.tasc.project.QLDT.business.sinh_vien.payload.response.KhoaAndKyHoc.KyHocResponse;
import com.tasc.project.QLDT.business.sinh_vien_khoa.ISinhVienKhoaRepository;
import com.tasc.project.QLDT.business.user.repository.IUserRepository;
import com.tasc.project.QLDT.common.constants.KyHocStatus;
import com.tasc.project.QLDT.model.academic.Khoa;
import com.tasc.project.QLDT.model.academic.KyHoc;
import com.tasc.project.QLDT.model.student.SinhVien;
import com.tasc.project.QLDT.model.student.SinhVienKhoa;
import com.tasc.project.QLDT.security.utils.SecurityUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SinhVienService {
    private final SecurityUtils securityUtils;
    private final ISinhVienKhoaRepository sinhVienKhoaRepository;
    private final KhoaToKhoaResponse khoa;
    private final KyHocToKyHocResponse kyHoc;
    private final IUserRepository userRepository;
    private final IKyHocRepository kyHocRepository;
    private final MonHocService monHocService;

    public KhoaAndKyHocResponse getInfomation(){
        KhoaAndKyHocResponse response = new KhoaAndKyHocResponse();
        List<KhoaResponse> khoaResponseList = getInfoKhoa();
        List<KyHocResponse> kyHocResponseList = getInfoKyHoc();
        response.setKhoaResponses(khoaResponseList);
        response.setKyHocResponses(kyHocResponseList);
        return response;
    }
    public List<KhoaResponse> getInfoKhoa(){
        SinhVien sinhVien = securityUtils.getCurrentSinhVien();
        List<SinhVienKhoa>  sinhVienKhoaList = sinhVien.getSinhVienKhoas();
        List<Khoa> khoaList = sinhVienKhoaList.stream().map((svk) -> svk.getKhoa()).collect(Collectors.toList());
        List<KhoaResponse> khoaResponses = khoaList.stream()
                .map((k) ->  khoa.toKhoaResponse(k)).collect(Collectors.toList());
        return khoaResponses;
    }
    //
    public List<KyHocResponse> getInfoKyHoc(){
        List<KyHoc> kyHocs = kyHocRepository.findByStatus(KyHocStatus.DANG_DANG_KY);
        List<KyHocResponse> kyHocResponses = kyHocs.stream()
                .map((kyhoc) -> kyHoc.toKyHocResponse(kyhoc))
                .collect(Collectors.toList());
        return kyHocResponses;
    }

    public List<MonDangKyResponse> getInfoMonDangKy(Long khoaId, Long kyHocId){
        SinhVien sinhVien = securityUtils.getCurrentSinhVien();
        List<MonDangKyResponse> responseList = monHocService.getInfoMonDangKy(sinhVien.getId(),khoaId,kyHocId);
        return responseList;
    }
}
