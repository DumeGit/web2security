package com.web2security.book;

import com.web2security.appuser.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    List<Book> findAllByUser(AppUser user);

    List<Book> findAllByNameAndUserNot(String name, AppUser user);
}
