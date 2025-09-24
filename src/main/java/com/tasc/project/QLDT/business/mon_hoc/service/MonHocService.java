package com.tasc.project.QLDT.business.mon_hoc.service;

import com.tasc.project.QLDT.business.mon_hoc.payload.response.MonDangKyResponse;
import com.tasc.project.QLDT.business.mon_hoc.payload.response.SoTinChiErrorResponse;
import com.tasc.project.QLDT.business.mon_hoc.repository.IMonHocRepository;
import com.tasc.project.QLDT.model.academic.MonHoc;
import com.tasc.project.QLDT.model.schedule.LopHocPhan;
import com.tasc.project.QLDT.security.utils.SecurityUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MonHocService {
    private final IMonHocRepository monHocRepository;
    private final SecurityUtils securityUtils;
    public List<MonDangKyResponse> getInfoMonDangKy(Long sinhVienId, Long khoaId, Long kyHocId){
        List<MonDangKyResponse> monDangKyResponses = monHocRepository.getInfoDangKy(sinhVienId,khoaId,kyHocId);
        return monDangKyResponses;
    }
    //
    public SoTinChiErrorResponse validateSoTinChi(List<LopHocPhan> lopHocPhans){
        SoTinChiErrorResponse soTinChiErrorResponse = new SoTinChiErrorResponse();
        int total = 0;
        for(LopHocPhan lhp : lopHocPhans){
            MonHoc mh  = monHocRepository.getMonHocByLhp(lhp.getId());
            total += mh.getSoTinChi();
        }
        if (total > securityUtils.MAX_TIN_CHI){
            soTinChiErrorResponse.setStatus(false);
        }
        soTinChiErrorResponse.setTongTinChi(total);
        return soTinChiErrorResponse;
    }
}
