package com.youkwhan.trade_view.service;

import com.youkwhan.trade_view.client.StockClient;
import com.youkwhan.trade_view.dto.favorite.FavoriteStockRequest;
import com.youkwhan.trade_view.dto.history.DailyStockResponse;
import com.youkwhan.trade_view.dto.history.StockHistoryResponse;
import com.youkwhan.trade_view.dto.overview.StockOverviewResponse;
import com.youkwhan.trade_view.dto.stock.AlphaVantageResponse;
import com.youkwhan.trade_view.dto.stock.StockResponse;
import com.youkwhan.trade_view.entity.FavoriteStock;
import com.youkwhan.trade_view.repository.FavoriteStockRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StockService {

    private final StockClient stockClient;
    private FavoriteStockRepository favoriteStockRepository;

    public StockService(final StockClient stockClient, final FavoriteStockRepository favoriteStockRepository) {
        this.stockClient = stockClient;
        this.favoriteStockRepository = favoriteStockRepository;
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

    @Transactional
    public FavoriteStock addFavorite(final String symbol) {
        if (favoriteStockRepository.existsBySymbol(symbol)) {
            throw new RuntimeException("Symbol already in stock" + symbol);
        }

        FavoriteStock favorite = FavoriteStock.builder().symbol(symbol).build();

        return favoriteStockRepository.save(favorite);
    }
}
