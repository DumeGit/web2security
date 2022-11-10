package com.web2security.appuser;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateAppUserDto {
    private String email;
    private String password;
    private Integer money;
}
