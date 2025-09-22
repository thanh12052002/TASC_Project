package com.tasc.project.QLDT.business.sinh_vien.payload.response.KhoaAndKyHoc;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.util.List;

@Getter
@Setter
public class KhoaAndKyHocResponse {
    private List<KhoaResponse> khoaResponses;
    private List<KyHocResponse> kyHocResponses;
}
