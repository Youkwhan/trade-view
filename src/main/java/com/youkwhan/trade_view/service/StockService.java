package com.youkwhan.trade_view.service;

import com.youkwhan.trade_view.client.StockClient;
import com.youkwhan.trade_view.dto.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StockService {

    private final StockClient stockClient;

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

    public List<DailyStockResponse> getHistoryForSymbol(String stockSymbol, int days) {
        StockHistoryResponse response = stockClient.getStockHistory(stockSymbol);
        return response.timeSeries().entrySet().stream()
                .limit(days) // limit the number of days on the client side bcz the api doesn't take it
                .map(entry -> {
                    var date = entry.getKey();
                    var daily = entry.getValue();
                    return new DailyStockResponse(
                            date,
                            Double.parseDouble(daily.open()),
                            Double.parseDouble(daily.high()),
                            Double.parseDouble(daily.low()),
                            Double.parseDouble(daily.close()),
                            Long.parseLong(daily.volume())
                    );
                })
                .collect(Collectors.toList());
    }
}
