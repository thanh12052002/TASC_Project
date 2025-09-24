package com.tasc.project.QLDT.business.lop_hoc_phan.payload.response;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class TrungLichErrorResponse {
    private Long lopHocPhanId1;
    private Long lopHocPhanId2;
    private int tuan,thu;
    private List<Integer> kipTrung;
}
