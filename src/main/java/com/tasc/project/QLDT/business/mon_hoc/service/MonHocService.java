package com.tasc.project.QLDT.business.mon_hoc.service;

import com.tasc.project.QLDT.business.mon_hoc.payload.response.MonDangKyResponse;
import com.tasc.project.QLDT.business.mon_hoc.repository.IMonHocRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MonHocService {
    private final IMonHocRepository monHocRepository;

    public List<MonDangKyResponse> getInfoMonDangKy(Long sinhVienId, Long khoaId, Long kyHocId){
        List<MonDangKyResponse> monDangKyResponses = monHocRepository.getInfoDangKy(sinhVienId,khoaId,kyHocId);
        return monDangKyResponses;
    }
    //

}
