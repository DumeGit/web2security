package com.web2security.appuser;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SendMoneyDto {
    private Integer money;
    private String receiver;
}
