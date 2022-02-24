package com.hakan.arayici.readingisgood.service;

import com.hakan.arayici.readingisgood.api.dto.BookDTO;
import com.hakan.arayici.readingisgood.api.dto.BookStockUpdateDTO;
import com.hakan.arayici.readingisgood.data.dao.BookRepository;
import com.hakan.arayici.readingisgood.data.model.BookEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.Optional;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

class BookServiceTest {

    @MockBean
    BookRepository bookRepository;

    @InjectMocks
    private BookService bookService;

    @BeforeEach
    public void setUp(){
        bookRepository = mock(BookRepository.class);
        bookService = new BookService(bookRepository);
    }

    @Test
    void createBookTest() {
        when(bookRepository.save(any(BookEntity.class))).thenReturn(null);

        Boolean success = bookService.createBook(BookDTO.builder()
                .title("Suc Ve Ceza")
                .stockCount(1)
                .author("Dostoyevski")
                .build());

        assertTrue(success);
        verify(bookRepository,times(1)).save(any(BookEntity.class));

    }

    @Test
    void updateBookStockTest() {
        when(bookRepository.save(any(BookEntity.class))).thenReturn(null);
        when(bookRepository.findById(anyLong())).thenReturn(Optional.of(BookEntity.builder()
                .build()));

        Boolean success = bookService.updateBookStock(BookStockUpdateDTO.builder()
                .id(1L)
                .stockCount(3)
                .build());

        assertTrue(success);
        verify(bookRepository,times(1)).save(any(BookEntity.class));
        verify(bookRepository,times(1)).findById(anyLong());
    }
}
