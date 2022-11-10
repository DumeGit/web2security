package com.web2security.authentication;

import com.web2security.appuser.AppUser;
import com.web2security.appuser.AppUserDto;
import com.web2security.appuser.AppUserRepository;
import com.web2security.security.AppRole;
import com.web2security.security.AppUserDetails;
import com.web2security.security.AppUserDetailsService;
import lombok.*;
import org.springframework.security.authentication.*;
import org.springframework.security.core.*;
import org.springframework.security.core.authority.*;
import org.springframework.security.core.context.*;
import org.springframework.security.crypto.password.*;
import org.springframework.stereotype.*;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final AppUserRepository appUserRepository;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;

    public AppUserDto getCurrentUser(String username) {
        AppUser appUser = appUserRepository.findByEmail(username).orElseThrow();
        return new AppUserDto(appUser.getEmail(), AppRole.USER, appUser.getMoney());
    }

    public void login(String username, String password) {
        AppUserDetails user = getUser(username, password);
        if(!user.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_USER"))) {
            throw new BadCredentialsException("Wrong credentials");
        }
        authenticateUser(username, password);
    }

    private AppUserDetails getUser(String username, String password) {
        AppUserDetails user = new AppUserDetails(appUserRepository.findByEmail(username)
                .orElseThrow(() -> new BadCredentialsException("Wrong credentials")));

        if(!username.equals(user.getUsername()) || !passwordEncoder.matches(password, user.getPassword())) {
            throw new BadCredentialsException("Wrong credentials");
        }
        return user;
    }

    private void authenticateUser(String username, String password) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(username, password)
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }
}
