package com.hakan.arayici.readingisgood.service;

import com.hakan.arayici.readingisgood.api.dto.BookDTO;
import com.hakan.arayici.readingisgood.api.dto.BookStockUpdateDTO;
import com.hakan.arayici.readingisgood.data.dao.BookRepository;
import com.hakan.arayici.readingisgood.data.model.BookEntity;
import com.hakan.arayici.readingisgood.exception.BookIsNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Slf4j
@Service
public class BookService implements IBookService{

    private final BookRepository bookRepository;

    @Override
    public Boolean createBook(BookDTO bookDTO) {

        bookRepository.save(BookEntity.builder()
                .author(bookDTO.getAuthor())
                .stockCount(bookDTO.getStockCount())
                .title(bookDTO.getTitle())
                .build());

        return Boolean.TRUE;
    }

    @Override
    public Boolean updateBookStock(BookStockUpdateDTO bookStockUpdateDTO) {

        BookEntity bookEntity  = bookRepository.findById(bookStockUpdateDTO.getId())
                .orElseThrow(() -> new BookIsNotFoundException(bookStockUpdateDTO.getId()));

        bookEntity.setStockCount(bookStockUpdateDTO.getStockCount());
        bookRepository.save(bookEntity);

        return Boolean.TRUE;
    }
}
