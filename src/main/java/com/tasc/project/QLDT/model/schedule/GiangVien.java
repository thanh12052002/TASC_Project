package com.tasc.project.QLDT.model.schedule;

import com.tasc.project.QLDT.model.academic.BoMon;
import com.tasc.project.QLDT.model.auth.User;
import jakarta.persistence.*;
import lombok.*;
import com.tasc.project.QLDT.common.constants.GiangVienPosition;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "giang_vien")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class GiangVien {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "vi_tri")
    @Enumerated(EnumType.STRING)
    private GiangVienPosition viTri;

    @ManyToOne
    @JoinColumn(name = "bo_mon_id")
    private BoMon boMon;

    @OneToMany(mappedBy = "giangVien")
    private List<LichHoc> lichHocs = new ArrayList<>();

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", unique = true)
    private User user;
}
