package com.hakan.arayici.readingisgood.service;

import com.hakan.arayici.readingisgood.api.dto.StatisticsDTO;

import java.util.List;

public interface IStatisticsService {
    List<StatisticsDTO> getStatisticsOfCustomer(String customerNo);
}
