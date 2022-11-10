package com.web2security.authentication;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginCommand {
    private String password;
    private String email;
}
