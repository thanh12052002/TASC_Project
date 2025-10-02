package com.tasc.project.QLDT.business.dang_ky_tam.converter;

import com.tasc.project.QLDT.business.dang_ky_tam.payload.response.DangKyTamDto;
import com.tasc.project.QLDT.model.academic.MonHoc;
import com.tasc.project.QLDT.model.schedule.LopHocPhan;
import com.tasc.project.QLDT.model.student.DangKyMonTam;

import java.util.List;
import java.util.stream.Collectors;

public class DangKyTamConvert {
    public static List<DangKyTamDto> toDangKyTamDto(List<DangKyMonTam> dangKyMonTams){
        List<DangKyTamDto> dangKyTamDtos = dangKyMonTams.stream()
                .map((dkt) -> convertToDangKyTamDto(dkt))
                .collect(Collectors.toList());
        return dangKyTamDtos;
    }
    private static DangKyTamDto convertToDangKyTamDto(DangKyMonTam dkt){
        DangKyTamDto dto = new DangKyTamDto();
        LopHocPhan lhp = dkt.getLopHocPhan();
        MonHoc mh = dkt.getLopHocPhan().getMonHocKyHoc().getMonHoc();
        //
        dto.setSoTinChi(mh.getSoTinChi());
        dto.setTenMonHoc(mh.getTen());
        dto.setLopHocPhanId(lhp.getId());
        dto.setTenLopHocPhan(lhp.getTen());
        return dto;
    }
}
