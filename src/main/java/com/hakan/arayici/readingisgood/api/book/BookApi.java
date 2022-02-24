package com.hakan.arayici.readingisgood.api.book;

import com.hakan.arayici.readingisgood.api.BookIsGoodApiResponse;
import com.hakan.arayici.readingisgood.api.dto.BookDTO;
import com.hakan.arayici.readingisgood.api.dto.BookStockUpdateDTO;
import com.hakan.arayici.readingisgood.service.BookService;
import com.hakan.arayici.readingisgood.service.IBookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class BookApi implements IBookApi{

    private final IBookService bookService;

    @Override
    public ResponseEntity<BookIsGoodApiResponse> createBook(BookDTO bookDTO) {
        bookService.createBook(bookDTO);
        return new ResponseEntity<>(new BookIsGoodApiResponse(true,""), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<BookIsGoodApiResponse> updateBookStock(BookStockUpdateDTO bookStockUpdateDTO) {
        bookService.updateBookStock(bookStockUpdateDTO);
        return new ResponseEntity<>(new BookIsGoodApiResponse(true,""), HttpStatus.OK);
    }
}
