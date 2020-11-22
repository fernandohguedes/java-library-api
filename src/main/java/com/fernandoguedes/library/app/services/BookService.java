package com.fernandoguedes.library.app.services;

import com.fernandoguedes.library.app.domain.dtos.BookDto;
import com.fernandoguedes.library.app.domain.entities.Book.Book;

public interface BookService {
    Book save(BookDto book);
}
