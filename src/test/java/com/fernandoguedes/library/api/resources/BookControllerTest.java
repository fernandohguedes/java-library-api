package com.fernandoguedes.library.api.resources;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fernandoguedes.library.app.domain.dtos.BookDto;
import com.fernandoguedes.library.app.domain.entities.Book.Book;
import com.fernandoguedes.library.app.services.BookService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.mockito.BDDMockito.*;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
@WebMvcTest
@AutoConfigureMockMvc
public class BookControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    BookService service;

    public static final String URI = "/api/books";

    @Test
    @DisplayName("Deve Criar um livro com sucesso")
    public void creteBook_withSucess() throws Exception {

        //Arrange
        BookDto book = BookDto.builder().author("Jorge").title("As Aventuras").isbn("001").build();
        Book savedBook = Book.builder().id(10l).author("Jorge").title("As Aventures").isbn("001").build();

        given(service.save(Mockito.any(BookDto.class))).willReturn(savedBook);
        String json = new ObjectMapper().writeValueAsString(book);

        // Act
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .post(URI)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(json);

        // Assert
        mockMvc.perform(request).andExpect(status().isCreated())
                .andExpect(jsonPath("id").isNotEmpty())
                .andExpect(jsonPath("title").value("As Aventuras"))
                .andExpect(jsonPath("author").value("Jorge"))
                .andExpect(jsonPath("isbn").value("001"));
    }

    @Test
    @DisplayName("Deve lançar erro de validação, através de uma exception tratada")
    public void createBook_WithError() {

    }
}
