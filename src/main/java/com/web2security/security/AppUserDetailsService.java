package com.web2security.security;

import com.web2security.appuser.AppUser;
import com.web2security.appuser.AppUserRepository;
import lombok.*;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.*;

@RequiredArgsConstructor
@Service
public class AppUserDetailsService implements UserDetailsService {
    private final AppUserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AppUser user = userRepository.findByEmail(username).orElseThrow();

        return new AppUserDetails(user);
    }
}
