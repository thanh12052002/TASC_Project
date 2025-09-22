package com.tasc.project.QLDT.common.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ErrorDetail {
    private String code;
    private String detail;

    public ErrorDetail(String code, String detail){
        this.code = code;
        this.detail = detail;
    }
}
