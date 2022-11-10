package com.web2security.security;
import com.web2security.appuser.AppUser;
import org.springframework.security.core.*;
import org.springframework.security.core.authority.*;
import org.springframework.security.core.userdetails.*;

import java.util.*;
import java.util.stream.*;

public class AppUserDetails implements UserDetails {


    private static final String ROLE_PREFIX = "ROLE_";

    private final String username;
    private final String password;
    private final List<GrantedAuthority> authorityList;

    public AppUserDetails(AppUser user) {
        this.username = user.getEmail();
        this.password = user.getPassword();
        this.authorityList = Collections.singletonList(new SimpleGrantedAuthority(user.getRole().toString()));
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorityList.stream()
                .map(role -> new SimpleGrantedAuthority(ROLE_PREFIX + role)).collect(Collectors.toList());
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
