package com.hakan.arayici.readingisgood.api.book;

import com.hakan.arayici.readingisgood.api.BookIsGoodApiResponse;
import com.hakan.arayici.readingisgood.api.dto.BookDTO;
import com.hakan.arayici.readingisgood.api.dto.BookStockUpdateDTO;
import com.hakan.arayici.readingisgood.api.dto.CustomerDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/api/book")
public interface IBookApi {

    @Operation(summary = "creates book", description = "creates book")
    @ApiResponse(description = "creates book")
    @PostMapping("/create")
    ResponseEntity<BookIsGoodApiResponse> createBook(@Validated @RequestBody BookDTO bookDTO);

    @Operation(summary = "updates stock of book", description = "updates stock of book")
    @ApiResponse(description = "updates stock of book")
    @PutMapping("/updateBookStock")
    ResponseEntity<BookIsGoodApiResponse> updateBookStock(@RequestBody @Validated BookStockUpdateDTO bookStockUpdateDTO);
}
