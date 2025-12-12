package com.youkwhan.trade_view.dto;

import lombok.Builder;

// records, gives us setters, getters, equals, hashcode, toString out of the box
@Builder
public record StockResponse(
        String symbol,
        double price,
        String lastUpdated
) {}
