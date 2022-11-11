package com.web2security.book;

import com.web2security.appuser.AppUser;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String genre;

    private Integer price;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="appuser_id", nullable=false)
    private AppUser user;

    public Book(String name, String genre, Integer price, AppUser user) {
        this.name = name;
        this.genre = genre;
        this.price = price;
        this.user = user;
    }
}
