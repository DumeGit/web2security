package com.web2security.appuser;

import com.web2security.security.AppRole;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AppUserDto {
    private String email;
    private AppRole role;
    private Integer money;


    public AppUserDto(AppUser user) {
        this.email = user.getEmail();
        this.role = user.getRole();
        this.money = user.getMoney();
    }
}
