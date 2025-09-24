package com.tasc.project.QLDT.business.mon_hoc.payload.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SoTinChiErrorResponse {
    private int tongTinChi;
    private boolean status = true;
}
