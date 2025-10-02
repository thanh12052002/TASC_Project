package com.tasc.project.QLDT.business.lop_hoc_phan.service;

import com.tasc.project.QLDT.business.lop_hoc_phan.convert.ConvertToLopHocPhanDto;
import com.tasc.project.QLDT.business.lop_hoc_phan.convert.LichHocConverter;
import com.tasc.project.QLDT.business.lop_hoc_phan.payload.response.*;
import com.tasc.project.QLDT.business.lop_hoc_phan.repository.ILopHocPhanRepository;
import com.tasc.project.QLDT.model.schedule.LichHoc;
import com.tasc.project.QLDT.model.schedule.LopHocPhan;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LopHocPhanService {
    private final ILopHocPhanRepository lopHocPhanRepository;
    private final ConvertToLopHocPhanDto convert;
    private final LichHocConverter lichHocConverter;
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

    public TrungLichResponse validateTrungLich(List<LopHocPhan> lopHocPhans){
        TrungLichResponse trungLichResponse= new TrungLichResponse();
        trungLichResponse.setStatus(true);
        for(int i = 0; i < lopHocPhans.size()-1; i++){
            LopHocPhan lhp1 = lopHocPhans.get(i);
            Map<Integer, Map<Integer, LichHocResponse>> lichHoc1 = lichHocConverter.toThoiGianMap(lhp1.getLichHocs());
            for(int j = i+1; j < lopHocPhans.size();j++){
                LopHocPhan lhp2 = lopHocPhans.get(j);
                Map<Integer,Map<Integer,LichHocResponse>> lichHoc2 = lichHocConverter.toThoiGianMap(lhp2.getLichHocs());
                //check tuan
                for(Integer tuan1 : lichHoc1.keySet()){
                    if (! lichHoc2.containsKey(tuan1)) continue;
                    //check thu
                    Map<Integer,LichHocResponse> mapThu1 = lichHoc1.get(tuan1);
                    Map<Integer,LichHocResponse> mapThu2 = lichHoc2.get(tuan1);
                    //
                    for(Integer thu1 : mapThu1.keySet()){
                        if (!mapThu2.containsKey(thu1)) continue;
                        //check kip
                        List<Integer> kips1 = mapThu1.get(thu1).getKips();
                        List<Integer> kips2 = mapThu2.get(thu1).getKips();
                        //
                        List<Integer> kipTrung = kips1.stream()
                                .filter(k1 -> kips2.contains(k1)).collect(Collectors.toList());
                        if (!kipTrung.isEmpty()){
                            TrungLichErrorResponse trungLichErrorResponse = new TrungLichErrorResponse();
                            trungLichErrorResponse.setLhp1(lhp1);
                            trungLichErrorResponse.setLhp2(lhp2);
                            trungLichErrorResponse.setTuan(tuan1);
                            trungLichErrorResponse.setThu(thu1);
                            trungLichErrorResponse.setKipTrung(kipTrung);
                            trungLichResponse.getLichErrorResponses().add(trungLichErrorResponse);
                        }
                    }
                }
                //
            }
        }
        if (! trungLichResponse.getLichErrorResponses().isEmpty()){
            trungLichResponse.setStatus(false);
        }
        return trungLichResponse;
    }

    public SlotResponse validateSlot(List<LopHocPhan> lopHocPhans){
        SlotResponse slotResponse = new SlotResponse();
        slotResponse.setStatus(true);
        List<SlotErrorResponse> erros = new ArrayList<>();
        for(LopHocPhan lhp : lopHocPhans){
            int slotConLai = lhp.getSiSoToiDa() - lhp.getSiSoDaDangKy() -1;
            if (slotConLai < 0){
                SlotErrorResponse error = new SlotErrorResponse();
                error.setLopHocPhanId(lhp.getId());
                error.setTenLopHocPhan(lhp.getTen());
                erros.add(error);
            }
        }
        if (!erros.isEmpty()){
            slotResponse.setStatus(false);
        }
        slotResponse.setErrorResponseList(erros);
        return slotResponse;
    }
}
