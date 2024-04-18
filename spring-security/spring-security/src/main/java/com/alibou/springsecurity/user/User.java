package com.alibou.springsecurity.user;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "_user")
public class User implements UserDetails {
    @Id
    @GeneratedValue
    private Integer id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;

    @Enumerated(EnumType.ORDINAL)  //@Enumated tell springboot this is enum field & enum type of ordinal i.e each constant assigned with index is 0,1,..
    // enumtype string take string value of enum )
    private Role role;
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true; //return type should be true other wise we are not able to connect to users
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;//return type should be true other wise we are not able to connect to users
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;//return type should be true other wise we are not able to connect to users
    }

    @Override
    public boolean isEnabled() {
        return true;//return type should be true other wise we are not able to connect to users
    }
}
