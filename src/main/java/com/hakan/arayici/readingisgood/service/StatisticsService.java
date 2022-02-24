package com.hakan.arayici.readingisgood.service;

import com.hakan.arayici.readingisgood.api.dto.StatisticsDTO;
import com.hakan.arayici.readingisgood.data.dao.StatisticsRepository;
import com.hakan.arayici.readingisgood.data.model.StatisticsEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.text.DateFormatSymbols;
import java.time.Month;
import java.time.format.TextStyle;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class StatisticsService implements IStatisticsService {

    private final StatisticsRepository statisticsRepository;

    @Override
    public List<StatisticsDTO> getStatisticsOfCustomer(String customerNo) {

        List<StatisticsEntity> statisticsEntityList = statisticsRepository.getStatisticsByCustomerNo(customerNo);

        return statisticsEntityList.stream()
                .map(statisticsEntity -> StatisticsDTO.builder()
                        .month(Month.of(statisticsEntity.getMonth()).getDisplayName(TextStyle.FULL_STANDALONE, Locale.US))
                        .totalBookCount(statisticsEntity.getTotalBookCount())
                        .totalPurchasedAmount(statisticsEntity.getTotalPrice())
                        .totalOrderCount(statisticsEntity.getOrderCount())
                        .build())
                .collect(Collectors.toList());
    }
}
