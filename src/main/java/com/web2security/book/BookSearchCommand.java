package com.web2security.book;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookSearchCommand {
    private String bookName;
    private boolean sqlInjection;
}
