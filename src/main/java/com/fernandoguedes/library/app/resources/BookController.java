package com.fernandoguedes.library.app.resources;

import com.fernandoguedes.library.app.domain.dtos.BookDto;
import com.fernandoguedes.library.app.domain.entities.Book.Book;
import com.fernandoguedes.library.app.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/books")
public class BookController {

    @Autowired
    private BookService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public BookDto create(@RequestBody BookDto request){
        service.save(request);

        return ;
    }
}
