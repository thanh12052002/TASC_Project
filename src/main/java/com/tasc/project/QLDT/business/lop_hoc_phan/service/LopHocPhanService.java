package com.tasc.project.QLDT.business.lop_hoc_phan.service;

import com.tasc.project.QLDT.business.lop_hoc_phan.convert.ConvertToLopHocPhanDto;
import com.tasc.project.QLDT.business.lop_hoc_phan.payload.response.LopHocPhanResponse;
import com.tasc.project.QLDT.business.lop_hoc_phan.repository.ILopHocPhanRepository;
import com.tasc.project.QLDT.model.schedule.LichHoc;
import com.tasc.project.QLDT.model.schedule.LopHocPhan;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class LopHocPhanService {
    private final ILopHocPhanRepository lopHocPhanRepository;
    private final ConvertToLopHocPhanDto convert;
    public List<LopHocPhanResponse> getInfoLopHocPhan(Long monHocKyHocId){
        List<LopHocPhanResponse> lopHocPhanResponses = new ArrayList<>();
        List<LopHocPhan> lopHocPhans = lopHocPhanRepository.getLopHocPhan(monHocKyHocId);
        for(LopHocPhan lhp : lopHocPhans){
            List<LichHoc>  lichHocs = lhp.getLichHocs();
            LopHocPhanResponse lopHocPhanResponse =  convert.toLopHocPhanResponse(lichHocs,lhp);
            lopHocPhanResponses.add(lopHocPhanResponse);
        }
        return lopHocPhanResponses;
    }
}
