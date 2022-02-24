package com.hakan.arayici.readingisgood.service;

import com.hakan.arayici.readingisgood.api.dto.BookDTO;
import com.hakan.arayici.readingisgood.api.dto.BookStockUpdateDTO;

public interface IBookService {

    Boolean createBook(BookDTO bookDTO);

    Boolean updateBookStock(BookStockUpdateDTO bookStockUpdateDTO);

}
