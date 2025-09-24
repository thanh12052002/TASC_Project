package com.tasc.project.QLDT.business.lop_hoc_phan.convert;

import com.tasc.project.QLDT.business.lop_hoc_phan.payload.response.LichHocResponse;
import com.tasc.project.QLDT.model.schedule.LichHoc;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class LichHocConverter {
    public Map<Integer, Map<Integer, LichHocResponse>> toThoiGianMap(List<LichHoc> lichHocList){
        Map<Integer,Map<Integer,LichHocResponse>> thoiGianHoc = new HashMap<>();
        for(LichHoc lh : lichHocList){
            Integer tuan = lh.getTuan();
            Integer thu = lh.getThu();
            Integer kip = lh.getKip();
            //
            if (!thoiGianHoc.containsKey(tuan)){
                thoiGianHoc.put(tuan,new HashMap<Integer,LichHocResponse>());
            }
            Map<Integer,LichHocResponse> mapThu  = thoiGianHoc.get(tuan);
            if(!mapThu.containsKey(thu)){
                LichHocResponse lichHocResponse = new LichHocResponse();
                lichHocResponse.setTenPhongHoc(lh.getPhongHoc().getTen());
                lichHocResponse.setTenGiangVien(lh.getGiangVien().getUser().getUserName());
                lichHocResponse.setKips(new ArrayList<>());
                mapThu.put(thu,lichHocResponse);
            }
            //
            mapThu.get(thu).getKips().add(kip);
        }
        for(Map.Entry<Integer,Map<Integer,LichHocResponse>> entry1 : thoiGianHoc.entrySet()){
            for(Map.Entry<Integer,LichHocResponse> entry2 : entry1.getValue().entrySet()){
                LichHocResponse lh = entry2.getValue();
                lh.getKips().sort(Comparator.naturalOrder());
            }
        }
        return thoiGianHoc;
    }
}
