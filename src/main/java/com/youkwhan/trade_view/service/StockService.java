package com.youkwhan.trade_view.service;

import com.youkwhan.trade_view.client.StockClient;
import com.youkwhan.trade_view.dto.StockResponse;
import org.springframework.stereotype.Service;

@Service
public class StockService {

    private StockClient stockClient;

    public StockService(final StockClient stockClient) {
        this.stockClient = stockClient;
    }

    public StockResponse getStockForSymbol(final String stockSymbol) {
        // Get our stock price from the AlphaAPI
        stockClient.getStockQuote(stockSymbol);

        return new StockResponse();
    }
}
