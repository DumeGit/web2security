package com.web2security.appuser;

import com.web2security.book.Book;
import com.web2security.security.AppRole;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor()
@Entity
@ToString(exclude = {"password"})
@EqualsAndHashCode(exclude = {"password"})
public class AppUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String password;

    @Column(unique = true)
    private String email;

    @Enumerated(EnumType.STRING)
    private AppRole role;

    private Integer money;

    @OneToMany(mappedBy = "user",  cascade = CascadeType.ALL)
    private List<Book> bookList;

    public AppUser (String email, String password, AppRole role){
        this.password = password;
        this.email = email;
        this.role = role;
    }


}
