package com.youkwhan.trade_view.dto.history;

public record DailyStockResponse(
        String date,
        double open,
        double high,
        double low,
        double close,
        long volume
) {}