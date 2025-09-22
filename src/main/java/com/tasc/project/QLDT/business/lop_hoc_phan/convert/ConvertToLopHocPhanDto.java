package com.tasc.project.QLDT.business.lop_hoc_phan.convert;

import com.tasc.project.QLDT.business.lop_hoc_phan.payload.response.LichHocResponse;
import com.tasc.project.QLDT.business.lop_hoc_phan.payload.response.LopHocPhanResponse;
import com.tasc.project.QLDT.model.schedule.LichHoc;
import com.tasc.project.QLDT.model.schedule.LopHocPhan;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
@RequiredArgsConstructor
public class ConvertToLopHocPhanDto {
    private final ModelMapper modelMapper;
    public  LopHocPhanResponse toLopHocPhanResponse(List<LichHoc> lichHocList, LopHocPhan lopHocPhan){
        Map<Integer, Map<Integer,LichHocResponse>> thoiGianHoc = new HashMap<>();
        for(LichHoc lh : lichHocList){
            Integer tuan = lh.getTuan();
            Integer thu = lh.getThu();
            Integer kip = lh.getKip();
            //
            String gv = lh.getGiangVien().getUser().getUserName();
            String phong = lh.getPhongHoc().getTen();
            //
            if(!thoiGianHoc.containsKey(tuan)){
                thoiGianHoc.put(tuan, new HashMap<Integer,LichHocResponse>());
            }
            Map<Integer,LichHocResponse> thuMap = thoiGianHoc.get(tuan);
            if (!thuMap.containsKey(thu)){
                LichHocResponse lichHocResponse = new LichHocResponse();
                lichHocResponse.setTenGiangVien(gv);
                lichHocResponse.setTenPhongHoc(phong);
                lichHocResponse.setKips(new ArrayList<Integer>());
                thuMap.put(thu,lichHocResponse);
            }
            //
            LichHocResponse lichHocResponse = thuMap.get(thu);
            lichHocResponse.getKips().add(kip);
        }
        //sort kip
        for (Map<Integer, LichHocResponse> thuMap : thoiGianHoc.values()) {
            for (LichHocResponse response : thuMap.values()) {
                response.getKips().sort(Comparator.naturalOrder());
            }
        }

        //
        LopHocPhanResponse lopHocPhanResponse = modelMapper.map(lopHocPhan,LopHocPhanResponse.class);
        lopHocPhanResponse.setThoiGianLichHoc(thoiGianHoc);
        return lopHocPhanResponse;
    }
}
