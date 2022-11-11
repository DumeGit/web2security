package com.web2security;

import com.web2security.appuser.AppUser;
import com.web2security.appuser.AppUserRepository;
import com.web2security.book.Book;
import com.web2security.book.BookRepository;
import com.web2security.security.AppRole;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@RequiredArgsConstructor
public class InitMockData implements ApplicationRunner {
    private final PasswordEncoder passwordEncoder;

    private final AppUserRepository appUserRepository;
    private final BookRepository bookRepository;

    @Override
    @Transactional
    public void run(ApplicationArguments args) throws Exception {
        addUsers();
        addBooks();
    }

    @Transactional
    public void addUsers() {
        AppUser appUser = new AppUser("nninic@mailinator.com", passwordEncoder.encode("web2pass"), AppRole.USER, 200);
        AppUser appUser2 = new AppUser("ttihic@mailinator.com", passwordEncoder.encode("web2pass"), AppRole.USER, 250);

        appUserRepository.saveAll(List.of(appUser, appUser2));
    }

    @Transactional
    public void addBooks() {
        Book book = new Book("Roboti", "Scifi", 50, appUserRepository.findByEmail("nninic@mailinator.com").orElseThrow());
        Book book1 = new Book("Zivotinje", "Enciklopedija", 20, appUserRepository.findByEmail("ttihic@mailinator.com").orElseThrow());
        Book book2 = new Book("Cudovista", "Horor", 30, appUserRepository.findByEmail("ttihic@mailinator.com").orElseThrow());
        Book book3 = new Book("Auti", "Bajka", 40, appUserRepository.findByEmail("ttihic@mailinator.com").orElseThrow());

        bookRepository.saveAll(List.of(book, book1, book2, book3));
    }
}
