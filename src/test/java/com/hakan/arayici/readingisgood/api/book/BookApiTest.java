package com.hakan.arayici.readingisgood.api.book;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.hakan.arayici.readingisgood.api.AbstractApiTest;
import com.hakan.arayici.readingisgood.api.dto.BookDTO;
import com.hakan.arayici.readingisgood.api.dto.BookStockUpdateDTO;
import com.hakan.arayici.readingisgood.config.AsciiDocConfiguration;
import com.hakan.arayici.readingisgood.exception.BookIsNotFoundException;
import com.hakan.arayici.readingisgood.service.BookService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;

import static org.mockito.Mockito.*;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc(addFilters = false)
@AutoConfigureRestDocs(outputDir = "target/generated-sources/snippets")
@Import(AsciiDocConfiguration.class)
@WebMvcTest(BookApi.class)
class BookApiTest extends AbstractApiTest {


    @MockBean
    private BookService bookService;

    @Test
    public void given_book_should_created() throws Exception {

        BookDTO book = BookDTO.builder()
                .author("Dostoyevski")
                .stockCount(4)
                .title("Suc ve Ceza")
                .build();

        when(bookService.createBook(any(BookDTO.class))).thenReturn(Boolean.TRUE);

        Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .create();

        mockMvc.perform(post("/api/book/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(gson.toJson(book))
                .characterEncoding("utf-8"))
                .andDo(print())
                .andExpect(status().isOk())
                .andDo(document("{method-name}",
                        requestFields(
                                fieldWithPath("author").description("author of the book"),
                                fieldWithPath("title").description("title of the book"),
                                fieldWithPath("stockCount").description("stock count of the book")
                        ),
                        responseFields(
                                fieldWithPath("success").description("is successfull"),
                                fieldWithPath("errorMessage").description("error message")

                        )
                ))
                .andReturn();

        verify(bookService,times(1)).createBook(any(BookDTO.class));

    }

    @Test
    public void given_book_should_update_stock_if_exists() throws Exception {

        BookStockUpdateDTO book = BookStockUpdateDTO.builder()
                .id(1L)
                .stockCount(4)
                .build();

        when(bookService.updateBookStock(any(BookStockUpdateDTO.class))).thenReturn(Boolean.TRUE);

        Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .create();

        mockMvc.perform(put("/api/book/updateBookStock")
                .contentType(MediaType.APPLICATION_JSON)
                .content(gson.toJson(book))
                .characterEncoding("utf-8"))
                .andDo(print())
                .andExpect(status().isOk())
                .andDo(document("{method-name}",
                        requestFields(
                                fieldWithPath("id").description("id of the book"),
                                fieldWithPath("stockCount").description("stock count of the book")
                        ),
                        responseFields(
                                fieldWithPath("success").description("is successfull"),
                                fieldWithPath("errorMessage").description("error message")

                        )
                ))
                .andReturn();

        verify(bookService,times(1)).updateBookStock(any(BookStockUpdateDTO.class));

    }


    @Test
    public void given_book_should__not_update_stock_if_not_exists() throws Exception {

        BookStockUpdateDTO book = BookStockUpdateDTO.builder()
                .id(1L)
                .stockCount(4)
                .build();

        when(bookService.updateBookStock(any(BookStockUpdateDTO.class))).thenThrow(BookIsNotFoundException.class);

        Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .create();

        mockMvc.perform(put("/api/book/updateBookStock")
                .contentType(MediaType.APPLICATION_JSON)
                .content(gson.toJson(book))
                .characterEncoding("utf-8"))
                .andDo(print())
                .andExpect(status().isNotFound())
                .andDo(document("{method-name}",
                        requestFields(
                                fieldWithPath("id").description("id of the book"),
                                fieldWithPath("stockCount").description("stock count of the book")
                        ),
                        responseFields(
                                fieldWithPath("success").description("is successfull"),
                                fieldWithPath("errorMessage").description("error message")

                        )
                ))
                .andReturn();

        verify(bookService,times(1)).updateBookStock(any(BookStockUpdateDTO.class));

    }
}