package com.web2security.appuser;

import com.web2security.book.BookDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.relational.core.sql.In;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/user")
public class AppUserController {

    private final AppUserService appUserService;
    private final AppUserRepository appUserRepository;

    @PostMapping("/create")
    public void create(@RequestBody CreateAppUserDto dto) {
        appUserService.create(dto);
    }

    @GetMapping("/getAll")
    public List<AppUserDto> getAll() {
        return appUserRepository.findAll().stream().map(AppUserDto::new).collect(Collectors.toList());
    }

    @GetMapping("/send")
    public void send(@RequestParam String email, @RequestParam Integer money, Authentication authentication) {
        appUserService.send(email, money, authentication.getName());
    }

    @PostMapping("/sendCsrf")
    public void send(@RequestBody SendMoneyDto dto, Authentication authentication) {
        appUserService.send(dto.getReceiver(), dto.getMoney(), authentication.getName());
    }

    @PostMapping("/sendUnsecured")
    public void sendUnsecured(@RequestBody SendMoneyDto dto, Authentication authentication) {
        appUserService.send(dto.getReceiver(), dto.getMoney(), authentication.getName());
    }
}
