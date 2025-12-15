package com.youkwhan.trade_view.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;

public record StockHistoryResponse(
        // mapping 2 records from this history response
        @JsonProperty("Meta Data") MetaData metaData,
        @JsonProperty("Time Series (Daily)") Map<String, DailyPrice> timeSeries
) {
    public record MetaData(
            @JsonProperty("2. Symbol") String symbol
    ){}

    public record DailyPrice(
            @JsonProperty("1. open") String open,
            @JsonProperty("2. high") String high,
            @JsonProperty("3. low") String low,
            @JsonProperty("4. close") String close,
            @JsonProperty("5. volume") String volume
    ){}
}
