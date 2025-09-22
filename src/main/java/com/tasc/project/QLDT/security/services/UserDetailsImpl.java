package com.tasc.project.QLDT.security.services;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.tasc.project.QLDT.model.auth.Role;
import com.tasc.project.QLDT.model.auth.User;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
public class UserDetailsImpl implements UserDetails {
    private final Long id;
    private final String username;
    private final String email;
    private final List<Role> roles;
    @JsonIgnore
    private String password;
    private final Collection<? extends GrantedAuthority> authorities;

    //constructor
    public UserDetailsImpl(Long id, String username, String email, String password, List<Role> roles, Collection<? extends GrantedAuthority> authorities){
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.roles = roles;
        this.authorities = authorities;
    }
    //
    public static UserDetailsImpl build(User user){
        List<Role> listRole = user.getRoles();

        List<GrantedAuthority> grantedAuthorities = listRole.stream()
                .map(role -> new SimpleGrantedAuthority(role.getName().name()))
                .collect(Collectors.toList());

        return new UserDetailsImpl(
                user.getId(),
                user.getUserName(),
                user.getEmail(),
                user.getPassword(),
                listRole,
                grantedAuthorities);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    public List<String> getRoles(){
        List<String> roles = this.roles.stream()
                .map((role) -> role.getName().name())
                .collect(Collectors.toList());
        return roles;
    }
}
