package com.hakan.arayici.readingisgood.api.statistics;

import com.hakan.arayici.readingisgood.api.AbstractApiTest;
import com.hakan.arayici.readingisgood.api.dto.StatisticsDTO;
import com.hakan.arayici.readingisgood.config.AsciiDocConfiguration;
import com.hakan.arayici.readingisgood.service.StatisticsService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;

import java.math.BigDecimal;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.requestParameters;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc(addFilters = false)
@AutoConfigureRestDocs(outputDir = "target/generated-sources/snippets")
@Import(AsciiDocConfiguration.class)
@WebMvcTest(StatisticsApi.class)
class StatisticsApiTest extends AbstractApiTest {

    @MockBean
    private StatisticsService statisticsService;

    @Test
    public void given_customer_no_should_return_statistics() throws Exception {

        List<StatisticsDTO> statisticsDTOList = List.of(StatisticsDTO.builder()
                .totalOrderCount(3)
                .month("January")
                .totalPurchasedAmount(BigDecimal.TEN)
                .totalBookCount(5)
                .build());

        when(statisticsService.getStatisticsOfCustomer(anyString())).thenReturn(statisticsDTOList);

        mockMvc.perform(get("/api/statistics/get?customerNo={customerNo}", "123" ))
                .andDo(print())
                .andExpect(status().isOk())
                .andDo(document("{method-name}",
                        requestParameters(parameterWithName("customerNo").description("number of customer")),
                        responseFields(
                                fieldWithPath("[].totalOrderCount").description("Order Count"),
                                fieldWithPath("[].month").description("month of Statistics"),
                                fieldWithPath("[].totalBookCount").description("Book Count"),
                                fieldWithPath("[].totalPurchasedAmount").description("purchased amount")
                        )
                ));

        verify(statisticsService,times(1)).getStatisticsOfCustomer(anyString());

    }

}