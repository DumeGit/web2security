package com.web2security.book;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BookDto {
    private Long id;
    private String name;
    private String genre;
    private Integer price;

    private Long appuserId;

    private String seller;

    public BookDto(Book book) {
        this.id = book.getId();
        this.name = book.getName();
        this.genre = book.getGenre();
        this.price = book.getPrice();
        this.seller = book.getUser().getEmail();
    }
}
