package com.tasc.project.QLDT.business.lop_hoc_phan.payload.response;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class TrungLichResponse {
    private boolean status;
    private List<TrungLichErrorResponse> lichErrorResponses = new ArrayList<>();
}
