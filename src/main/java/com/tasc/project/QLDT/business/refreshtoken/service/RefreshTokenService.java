package com.tasc.project.QLDT.business.refreshtoken.service;

import com.tasc.project.QLDT.business.refreshtoken.repository.IRefreshTokenRepository;
import com.tasc.project.QLDT.business.user.repository.IUserRepository;
import com.tasc.project.QLDT.model.auth.RefreshToken;
import com.tasc.project.QLDT.model.auth.User;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RefreshTokenService {
    private final IUserRepository userRepository;
    private final IRefreshTokenRepository refreshTokenRepository;

    @Value("${app.jwtRefreshToken}")
    private int jwtExpirationMsRefreshToken;

    public RefreshToken createOrReplaceRefreshToken(String username){
        User user = userRepository.findByUserName(username)
                .orElseThrow(() -> new UsernameNotFoundException("Not Found User" +username));
        RefreshToken refreshToken = new RefreshToken();
        refreshToken.setUser(user);
        refreshToken.setToken(UUID.randomUUID().toString());
        refreshToken.setExpiryDate(LocalDateTime.now()
                .plusSeconds(jwtExpirationMsRefreshToken / 1000));
        return refreshTokenRepository.save(refreshToken);
    }
}
