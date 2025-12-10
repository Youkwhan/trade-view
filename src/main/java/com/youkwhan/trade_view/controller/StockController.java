package com.youkwhan.trade_view.controller;

import com.youkwhan.trade_view.dto.StockResponse;
import com.youkwhan.trade_view.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
