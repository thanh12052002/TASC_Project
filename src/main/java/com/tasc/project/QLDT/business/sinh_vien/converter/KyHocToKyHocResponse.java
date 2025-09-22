package com.tasc.project.QLDT.business.sinh_vien.converter;

import com.tasc.project.QLDT.business.sinh_vien.payload.response.KhoaAndKyHoc.KyHocResponse;
import com.tasc.project.QLDT.model.academic.KyHoc;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class KyHocToKyHocResponse {
    ModelMapper modelMapper;
    public KyHocResponse toKyHocResponse(KyHoc kyHoc){
        KyHocResponse kyHocResponse = new KyHocResponse();
        String name = kyHoc.getHocKy().getMoTa() + kyHoc.getNamHoc().getMoTa();
        kyHocResponse.setTen(name);
        kyHocResponse.setId(kyHoc.getId());
        return kyHocResponse;
    }
}
