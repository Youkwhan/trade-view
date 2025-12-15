package com.youkwhan.trade_view.controller;

import com.youkwhan.trade_view.dto.DailyStockResponse;
import com.youkwhan.trade_view.dto.StockOverviewResponse;
import com.youkwhan.trade_view.dto.StockResponse;
import com.youkwhan.trade_view.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/stocks")
public class StockController {
    private final StockService stockService;

    @Autowired
    public StockController(final StockService stockService) {
        this.stockService = stockService;
    }

    @GetMapping("/{stockSymbol}")
    public StockResponse getStock(@PathVariable String stockSymbol) {
        return stockService.getStockForSymbol(stockSymbol.toUpperCase());
    }

    @GetMapping("/{stockSymbol}/overview")
    public StockOverviewResponse getStockOverview(@PathVariable String stockSymbol) {
        return stockService.getStockOverviewForSymbol(stockSymbol.toUpperCase());
    }

    @GetMapping("/{stockSymbol}/history")
    public List<DailyStockResponse> getStockHistory(
            @PathVariable String stockSymbol,
            @RequestParam(defaultValue = "30") int days
    ) {
        return stockService.getHistoryForSymbol(stockSymbol.toUpperCase(), days);
    }
}
