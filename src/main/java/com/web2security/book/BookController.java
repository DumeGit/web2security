package com.web2security.book;

import com.web2security.appuser.AppUser;
import com.web2security.appuser.AppUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/book")
public class BookController {

    private final JdbcTemplate jdbcTemplate;
    private final BookRepository bookRepository;
    private final AppUserRepository appUserRepository;

    @PostMapping("/search")
    public @ResponseBody List<BookDto> search(@RequestBody BookSearchCommand dto, Authentication authentication) {
        AppUser user = appUserRepository.findByEmail(authentication.getName()).orElseThrow();
        List<BookDto> list;
        if(dto.isSqlInjection()) {
            String sql = "select "
                    + "*"
                    + "from book where name = '"
                    + dto.getBookName()
                    + "'"
                    + " and appuser_id != "
                    + user.getId()
                    ;

            list = jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(BookDto.class));
            list.forEach(bookDto -> bookDto.setSeller(appUserRepository.findById(bookDto.getAppuserId()).orElseThrow().getEmail()));
        } else {
            list = bookRepository.findAllByNameAndUserNot(dto.getBookName(), user).stream().map(BookDto::new).collect(Collectors.toList());
        }


        return list;
    }

    @PostMapping("/searchMyBooks")
    public @ResponseBody List<BookDto> searchMyBooks(Authentication authentication) {
        return bookRepository.findAllByUser(appUserRepository.findByEmail(authentication.getName()).orElseThrow()).stream().map(BookDto::new).collect(Collectors.toList());
    }

    @Secured({"ROLE_USER"})
    @GetMapping("/buy")
    public ResponseEntity buy(@RequestParam Long bookId, Authentication authentication) {
        AppUser buyer = appUserRepository.findByEmail(authentication.getName()).orElseThrow();
        Book book = bookRepository.findById(bookId).orElseThrow();
        AppUser seller = appUserRepository.findByEmail(book.getUser().getEmail()).orElseThrow();

        buyer.setMoney(buyer.getMoney() - book.getPrice());
        appUserRepository.save(buyer);

        seller.setMoney(seller.getMoney() + book.getPrice());
        appUserRepository.save(seller);

        book.setUser(buyer);
        bookRepository.save(book);
        return ResponseEntity.ok().build();
    }
}
