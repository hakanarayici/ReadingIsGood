package com.hakan.arayici.readingisgood.api.statistics;

import com.hakan.arayici.readingisgood.api.BookIsGoodApiResponse;
import com.hakan.arayici.readingisgood.api.dto.StatisticsDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@RequestMapping("/api/statistics")
public interface IStatisticsApi {

    @Operation(summary = "gets statistics of customer", description = "gets statistics of customer")
    @ApiResponse(description = "gets statistics of customer")
    @GetMapping("/get")
    ResponseEntity<List<StatisticsDTO>> getByCustomerNo(@RequestParam String customerNo);

}
