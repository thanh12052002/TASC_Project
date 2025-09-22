package com.tasc.project.QLDT.business.auth.mapper;

import com.tasc.project.QLDT.business.auth.payload.response.UserInfoDto;
import com.tasc.project.QLDT.security.services.UserDetailsImpl;

public class UserDetailsConvert {
    public static UserInfoDto ToUserInfoDto(UserDetailsImpl userDetails){
            UserInfoDto userInfoDto = new UserInfoDto();
            userInfoDto.setEmail(userDetails.getEmail());
            userInfoDto.setUsername(userDetails.getUsername());
            userInfoDto.setRoles(userDetails.getRoles());
            return userInfoDto;
    }
}
