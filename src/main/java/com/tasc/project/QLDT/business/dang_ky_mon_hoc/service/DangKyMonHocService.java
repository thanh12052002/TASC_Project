package com.tasc.project.QLDT.business.dang_ky_mon_hoc.service;

import com.tasc.project.QLDT.business.dang_ky_mon_hoc.payload.response.DangKyResponse;
import com.tasc.project.QLDT.business.dang_ky_mon_hoc.repository.IDangKyMonHoc;
import com.tasc.project.QLDT.business.dang_ky_tam.service.DangKyTamService;
import com.tasc.project.QLDT.business.lop_hoc_phan.payload.response.SlotErrorResponse;
import com.tasc.project.QLDT.business.lop_hoc_phan.payload.response.SlotResponse;
import com.tasc.project.QLDT.business.lop_hoc_phan.payload.response.TrungLichErrorResponse;
import com.tasc.project.QLDT.business.lop_hoc_phan.payload.response.TrungLichResponse;
import com.tasc.project.QLDT.business.lop_hoc_phan.service.LopHocPhanService;
import com.tasc.project.QLDT.business.mon_hoc.payload.response.SoTinChiErrorResponse;
import com.tasc.project.QLDT.business.mon_hoc.service.MonHocService;
import com.tasc.project.QLDT.model.schedule.LopHocPhan;
import com.tasc.project.QLDT.model.student.DangKyMonHoc;
import com.tasc.project.QLDT.model.student.DangKyMonTam;
import com.tasc.project.QLDT.security.utils.SecurityUtils;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DangKyMonHocService {
    private final DangKyTamService dangKyTamService;
    private final LopHocPhanService lopHocPhanService;
    private final MonHocService monHocService;
    private final SecurityUtils securityUtils;
    private final IDangKyMonHoc iDangKyMonHoc;
    @Transactional
    public DangKyResponse confirmDangKy(Long sinhVienKhoaId){
        DangKyResponse dangKyResponse = new DangKyResponse();
        dangKyResponse.setStatus(true);
        List<DangKyMonTam> dangKyMonTams = dangKyTamService.getPickTamList(sinhVienKhoaId);
        List<LopHocPhan> lopHocPhans = dangKyMonTams.stream()
                .map(dkt -> dkt.getLopHocPhan())
                .collect(Collectors.toList());
        //check tin chi
        SoTinChiErrorResponse soTinChiErrorResponse = monHocService.validateSoTinChi(lopHocPhans);
        if (!soTinChiErrorResponse.isStatus()){
            dangKyResponse.setStatus(false);
            String message = "Tổng tín chí của bạn là: " +soTinChiErrorResponse.getTongTinChi()
                    + "đang vượt quá quy định là: " + SecurityUtils.MAX_TIN_CHI;
            dangKyResponse.setMessage(message);
        }
        if (! dangKyResponse.isStatus()){
            return dangKyResponse;
        }
        //check slot
        SlotResponse slot = lopHocPhanService.validateSlot(lopHocPhans);
        if (!slot.isStatus()){
            dangKyResponse.setStatus(false);
            List<SlotErrorResponse> slotErrorResponses = slot.getErrorResponseList();
            String lopHocPhanError = slotErrorResponses.stream()
                    .map(s -> s.getTenLopHocPhan())
                    .collect(Collectors.joining(", "));
            String message = "Các lớp học phần đăng ký đã hết slot: " + lopHocPhanError;
            dangKyResponse.setMessage(message);
        }
        if (!dangKyResponse.isStatus()){
            return dangKyResponse;
        }
        //check trung lich hoc
        TrungLichResponse trungLichResponse = lopHocPhanService.validateTrungLich(lopHocPhans);
        if (!trungLichResponse.isStatus()){
            dangKyResponse.setStatus(false);
            List<TrungLichErrorResponse> trungLichErrorResponses = trungLichResponse.getLichErrorResponses();
            String message  = "Cac lop hoc phan bi trung lich";
            dangKyResponse.setMessage(message);
        }
        if (!dangKyResponse.isStatus()){
            return dangKyResponse;
        }
        //save dang_ky_mon
        for(DangKyMonTam dangKyMonTam : dangKyMonTams){
            DangKyMonHoc dangKyMonHoc = new DangKyMonHoc();
            dangKyMonHoc.setLopHocPhan(dangKyMonTam.getLopHocPhan());
            dangKyMonHoc.setSinhVienKhoa(dangKyMonTam.getSinhVienKhoa());
            dangKyMonHoc.setCreatedAt(LocalDateTime.now());
            iDangKyMonHoc.save(dangKyMonHoc);
        }
        dangKyResponse.setMessage("Đăng ký thành công");
        return dangKyResponse;
    }
}
