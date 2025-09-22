package com.tasc.project.QLDT.business.sinh_vien.converter;


import com.tasc.project.QLDT.business.sinh_vien.payload.response.KhoaAndKyHoc.KhoaResponse;
import com.tasc.project.QLDT.model.academic.Khoa;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;
import org.modelmapper.ModelMapper;

@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class KhoaToKhoaResponse {
    ModelMapper modelMapper;
    //
    public KhoaResponse toKhoaResponse(Khoa khoa){
        KhoaResponse khoaResponse = modelMapper.map(khoa,KhoaResponse.class);
        return khoaResponse;
    }
}
