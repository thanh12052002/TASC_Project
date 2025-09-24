package com.tasc.project.QLDT.business.dang_ky_tam.service;

import com.tasc.project.QLDT.business.dang_ky_tam.payload.response.DangKyTamDto;
import com.tasc.project.QLDT.business.dang_ky_tam.payload.response.DangKyTamResponse;
import com.tasc.project.QLDT.business.dang_ky_tam.repository.IDangKyTamRepository;
import com.tasc.project.QLDT.business.lop_hoc_phan.repository.ILopHocPhanRepository;
import com.tasc.project.QLDT.business.sinh_vien_khoa.ISinhVienKhoaRepository;
import com.tasc.project.QLDT.common.constants.DangKyTamStatus;
import com.tasc.project.QLDT.model.academic.MonHocKyHoc;
import com.tasc.project.QLDT.model.schedule.LopHocPhan;
import com.tasc.project.QLDT.model.student.DangKyMonTam;
import com.tasc.project.QLDT.model.student.SinhVienKhoa;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class DangKyTamService {
    private final IDangKyTamRepository dangKyTamRepository;
    private final ILopHocPhanRepository lopHocPhanRepository;
    private final ISinhVienKhoaRepository sinhVienKhoaRepository;

    @Transactional
    public DangKyTamResponse checkInfoDangKyTam(Long sinhVienKhoaId, Long lopHocPhanId){
        DangKyTamResponse response = new DangKyTamResponse();
        response.setStatus(true);
        //case 1
        dangKyTamRepository.getDangKyTam(sinhVienKhoaId,lopHocPhanId)
                .ifPresent((dkt) -> {
                    response.setStatus(false);
                    response.setMessage("Lớp học phần đã được đăng ký vào: " +
                            dkt.getNgayChon().toLocalTime().toString());
                });
        if (!response.isStatus()){
            return response;
        }
        //case 2
        MonHocKyHoc monHocKyHoc = lopHocPhanRepository.getLopHocPhanAndMonHocKyHoc(lopHocPhanId).getMonHocKyHoc();
        List<DangKyMonTam> dangKyMonTams = dangKyTamRepository.findAllDangKyTam(sinhVienKhoaId);
        Optional<DangKyMonTam>  dangKyMonTam=  dangKyMonTams.stream()
                .filter((dkt) -> {
                    return dkt.getLopHocPhan().getMonHocKyHoc().equals(monHocKyHoc);
                }).findFirst();
        dangKyMonTam.ifPresent((dkt) -> {
            response.setStatus(false);
            response.setMessage("Môn học đã được đăng ký bởi lớp học phần" + dkt.getLopHocPhan().getTen());
        });
        if(!response.isStatus()){
            return response;
        }
        //
        DangKyMonTam newDangKy  = new DangKyMonTam();
        SinhVienKhoa svk = sinhVienKhoaRepository.findById(sinhVienKhoaId)
                .orElseThrow(() -> new RuntimeException("Not found sinh vien"));
        LopHocPhan lhp = lopHocPhanRepository.findById(lopHocPhanId)
                .orElseThrow(() -> new RuntimeException("Not found Lop Hoc Phan"));
        newDangKy.setSinhVienKhoa(svk);
        newDangKy.setLopHocPhan(lhp);
        newDangKy.setNgayChon(LocalDateTime.now());
        newDangKy.setTrangThai(DangKyTamStatus.CHO_DUYET);
        //
        dangKyTamRepository.save(newDangKy);
        response.setMessage("Chọn lớp học phần thành công");
        //
        DangKyTamDto dto = new DangKyTamDto();
        dto.setLopHocPhanId(lopHocPhanId);
        dto.setTenLopHocPhan(lhp.getTen());
        dto.setTenMonHoc(lhp.getMonHocKyHoc().getMonHoc().getTen());
        dto.setSoTinChi(lhp.getMonHocKyHoc().getMonHoc().getSoTinChi());
        response.setThongTinDangKy(dto);
        return response;
    }

    public List<DangKyMonTam> getPickTamList(Long sinhVienKhoaId){
        List<DangKyMonTam> dangKyMonTams = dangKyTamRepository.findAllDangKyTam(sinhVienKhoaId);
        return dangKyMonTams;
    }
    public void removePickTam(Long sinhVienId){

    }
}
