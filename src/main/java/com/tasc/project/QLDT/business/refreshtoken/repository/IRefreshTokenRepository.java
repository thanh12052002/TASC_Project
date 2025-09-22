package com.tasc.project.QLDT.business.refreshtoken.repository;

import com.tasc.project.QLDT.model.auth.RefreshToken;
import com.tasc.project.QLDT.model.auth.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface IRefreshTokenRepository extends JpaRepository<RefreshToken,Long> {
    Optional<RefreshToken> findByToken(String token);
    void deleteByUser(User user);
    List<RefreshToken> findAllByUser(User user);

    Optional<RefreshToken> findByUser(User user);

    @Query("SELECT r FROM RefreshToken r WHERE r.user.userName = :username")
    List<RefreshToken> findByUsername(@Param("username") String username);
}
