package com.youkwhan.trade_view.client;

import com.youkwhan.trade_view.dto.stock.AlphaVantageResponse;
import com.youkwhan.trade_view.dto.history.StockHistoryResponse;
import com.youkwhan.trade_view.dto.overview.StockOverviewResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

// @Component
@Service
@RequiredArgsConstructor // from lombok
public class StockClient {

    private final WebClient webClient;

    @Value("${alpha.vantage.api.key}")
    private String apiKey;

    public AlphaVantageResponse getStockQuote(final String stockSymbol) {
        return webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/query")
                        .queryParam("function", "GLOBAL_QUOTE")
                        .queryParam("symbol", stockSymbol)
                        .queryParam("apikey", apiKey)
                        .build())
                .retrieve()
                .bodyToMono(AlphaVantageResponse.class)
                .block();
    }

    public StockOverviewResponse getStockOverview(final String stockSymbol) {
        return webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/query")
                        .queryParam("function", "OVERVIEW")
                        .queryParam("symbol", stockSymbol)
                        .queryParam("apikey", apiKey)
                        .build())
                .retrieve()
                .bodyToMono(StockOverviewResponse.class)
                .block();
    }

    public StockHistoryResponse getStockHistory(final String stockSymbol) {
        return webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .queryParam("function", "TIME_SERIES_DAILY")
                        .queryParam("symbol", stockSymbol)
                        .queryParam("apikey", apiKey)
                        .build())
                .retrieve()
                .bodyToMono(StockHistoryResponse.class)
                .block();
    }
}
