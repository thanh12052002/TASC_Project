package com.tasc.project.QLDT.business.user.repository;

import com.tasc.project.QLDT.model.auth.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IUserRepository extends JpaRepository<User,Long> {

    Optional<User> findByUserName(String userName);
}
