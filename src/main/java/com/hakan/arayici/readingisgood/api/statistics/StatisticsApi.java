package com.hakan.arayici.readingisgood.api.statistics;

import com.hakan.arayici.readingisgood.api.dto.StatisticsDTO;
import com.hakan.arayici.readingisgood.service.IStatisticsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class StatisticsApi implements IStatisticsApi{

    private final IStatisticsService statisticsService;

    @Override
    public ResponseEntity<List<StatisticsDTO>> getByCustomerNo(String customerNo) {
        List<StatisticsDTO> statisticsDTOList = statisticsService.getStatisticsOfCustomer(customerNo);
        return new ResponseEntity<>(statisticsDTOList, HttpStatus.OK);
    }
}
