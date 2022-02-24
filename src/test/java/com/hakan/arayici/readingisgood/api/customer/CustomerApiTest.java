package com.hakan.arayici.readingisgood.api.customer;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.hakan.arayici.readingisgood.api.AbstractApiTest;
import com.hakan.arayici.readingisgood.api.dto.CustomerDTO;
import com.hakan.arayici.readingisgood.api.dto.OrderBookDTO;
import com.hakan.arayici.readingisgood.api.dto.OrderDTO;
import com.hakan.arayici.readingisgood.config.AsciiDocConfiguration;
import com.hakan.arayici.readingisgood.service.CustomerService;
import com.hakan.arayici.readingisgood.service.OrderService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;

import java.time.LocalDateTime;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.requestParameters;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc(addFilters = false)
@AutoConfigureRestDocs(outputDir = "target/generated-sources/snippets")
@Import(AsciiDocConfiguration.class)
@WebMvcTest(CustomerApi.class)
class CustomerApiTest extends AbstractApiTest {

    @MockBean
    private CustomerService customerService;

    @MockBean
    private OrderService orderService;

    @Test
    public void given_customer_should_created() throws Exception {

        CustomerDTO customerDTO = CustomerDTO.builder()
                .customerName("Hakan Arayici")
                .customerNo("1")
                .build();

        when(customerService.createCustomer(any(CustomerDTO.class))).thenReturn(Boolean.TRUE);

        Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .create();

        mockMvc.perform(post("/api/customer/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(gson.toJson(customerDTO))
                .characterEncoding("utf-8"))
                .andDo(print())
                .andExpect(status().isOk())
                .andDo(document("{method-name}",
                        requestFields(
                                fieldWithPath("customerName").description("name of customer"),
                                fieldWithPath("customerNo").description("customer no of customer")
                        ),
                        responseFields(
                                fieldWithPath("success").description("is successfull"),
                                fieldWithPath("errorMessage").description("error message")

                        )
                ))
                .andReturn();

        verify(customerService,times(1)).createCustomer(any(CustomerDTO.class));

    }

    @Test
    public void given_customer_should_return_orders() throws Exception {

        List<OrderDTO> orderList = List.of(OrderDTO.builder()
                .orderID(1L)
                .orderDate(LocalDateTime.now())
                .customerNo("1")
                .orderID(1L)
                .bookDTOList(List.of(OrderBookDTO.builder().build()))
                .build());

        when(orderService.getByCustomerNo(anyString(),anyInt(),anyInt())).thenReturn(orderList);

        mockMvc.perform(get("/api/customer/getOrdersByCustomerNo?customerNo={customerNo}&page={page}&pageSize={pageSize}", "123","1","1" ))
                .andDo(print())
                .andExpect(status().isOk())
                .andDo(document("{method-name}",
                        requestParameters(
                                    parameterWithName("customerNo").description("number of customer"),
                                    parameterWithName("pageSize").description("page size"),
                                    parameterWithName("page").description("page")
                                ),
                        responseFields(
                                fieldWithPath("[].orderID").description("Order Count"),
                                fieldWithPath("[].orderDate").description("month of Statistics"),
                                fieldWithPath("[].customerNo").description("Book Count"),
                                fieldWithPath("[].bookDTOList[].title").description("purchased amount"),
                                fieldWithPath("[].bookDTOList[].author").description("purchased amount"),
                                fieldWithPath("[].bookDTOList[].unitPrice").description("purchased amount"),
                                fieldWithPath("[].bookDTOList[].quantity").description("purchased amount")

                        )
                ));

        verify(orderService,times(1)).getByCustomerNo(anyString(),anyInt(),anyInt());

    }

}