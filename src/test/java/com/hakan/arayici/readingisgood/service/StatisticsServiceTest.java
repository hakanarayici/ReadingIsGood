package com.hakan.arayici.readingisgood.service;

import com.hakan.arayici.readingisgood.api.dto.StatisticsDTO;
import com.hakan.arayici.readingisgood.data.dao.StatisticsRepository;
import com.hakan.arayici.readingisgood.data.model.StatisticsEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

class StatisticsServiceTest {

    @MockBean
    StatisticsRepository statisticsRepository;

    @InjectMocks
    private StatisticsService statisticsService;

    @BeforeEach
    public void setUp(){
        statisticsRepository = mock(StatisticsRepository.class);
        statisticsService = new StatisticsService(statisticsRepository);
    }

    @Test
    void getStatisticsTest() {

        List<StatisticsEntity> statisticsEntityList = List.of(StatisticsEntity.builder()
                .customerNo("2312")
                .month(1)
                .orderCount(1)
                .totalBookCount(1)
                .totalPrice(BigDecimal.ONE)
                .build());

        when(statisticsRepository.getStatisticsByCustomerNo(anyString())).thenReturn(statisticsEntityList);

        List<StatisticsDTO> statisticsDTOList = statisticsService.getStatisticsOfCustomer("3213124");

        assertNotNull(statisticsDTOList);
        assertEquals(1,statisticsDTOList.size());
        verify(statisticsRepository,times(1)).getStatisticsByCustomerNo(anyString());

    }
}