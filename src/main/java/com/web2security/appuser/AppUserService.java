package com.web2security.appuser;

import com.web2security.authentication.AuthenticationService;
import com.web2security.security.AppRole;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AppUserService {
    private final PasswordEncoder passwordEncoder;
    private final AppUserRepository appUserRepository;

    private final AuthenticationService authenticationService;

    public void create(CreateAppUserDto dto) {
        AppUser user = new AppUser();
        user.setEmail(dto.getEmail());
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        user.setRole(AppRole.USER);

        appUserRepository.save(user);
    }

    public void send(String receiverEmail, Integer money, String senderEmail) {
        AppUser receiver = appUserRepository.findByEmail(receiverEmail).orElseThrow();
        AppUser sender = appUserRepository.findByEmail(senderEmail).orElseThrow();

        receiver.setMoney(receiver.getMoney() + money);
        sender.setMoney(sender.getMoney() - money);

        appUserRepository.saveAll(List.of(receiver, sender));
    }
}
