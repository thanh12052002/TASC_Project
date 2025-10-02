package com.tasc.project.QLDT.business.lop_hoc_phan.payload.response;

import com.tasc.project.QLDT.model.schedule.LopHocPhan;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class TrungLichErrorResponse {
    private LopHocPhan lhp1;
    private LopHocPhan lhp2;
    private int tuan,thu;
    private List<Integer> kipTrung;
}
