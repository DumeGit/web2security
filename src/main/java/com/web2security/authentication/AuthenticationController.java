package com.web2security.authentication;

import com.web2security.appuser.AppUserDto;
import lombok.*;
import org.springframework.http.*;
import org.springframework.security.core.*;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @GetMapping("/user")
    public ResponseEntity<AppUserDto> getCurrentUser(Authentication authentication) {

        return ResponseEntity.ok(authenticationService.getCurrentUser(authentication.getName()));
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginCommand cmd) {
        authenticationService.login(cmd.getEmail(), cmd.getPassword());
        return ResponseEntity.ok().body(cmd);
    }

    @GetMapping("/logout")
    public ResponseEntity<?> logout() {
        SecurityContextHolder.clearContext();
        return ResponseEntity.ok().build();
    }

}
