package com.youkwhan.trade_view.service;

import com.youkwhan.trade_view.client.StockClient;
import com.youkwhan.trade_view.dto.AlphaVantageResponse;
import com.youkwhan.trade_view.dto.StockOverviewResponse;
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
        final AlphaVantageResponse response = stockClient.getStockQuote(stockSymbol);

        return StockResponse.builder()
                .symbol(response.globalQuote().symbol())
                .price(Double.parseDouble(response.globalQuote().price()))
                .lastUpdated(response.globalQuote().lastTradingDay())
                .build();
    }

    public StockOverviewResponse getStockOverviewForSymbol(final String stockSymbol) {
        return stockClient.getStockOverview(stockSymbol);
    }
}
