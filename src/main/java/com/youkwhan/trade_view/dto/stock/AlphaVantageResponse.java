package com.youkwhan.trade_view.dto.stock;

import com.fasterxml.jackson.annotation.JsonProperty;

// Remember to only use records to represent immutable objects
public record AlphaVantageResponse(@JsonProperty("Global Quote") GlobalQuote globalQuote) {
    public record GlobalQuote(
            @JsonProperty("01. symbol") String symbol,
//            @JsonProperty("02. open") String open,
//            @JsonProperty("03. high") String high,
//            @JsonProperty("04. low") String low,
            @JsonProperty("05. price") String price,
//            @JsonProperty("06. volume") String volume,
            @JsonProperty("07. latest trading day") String lastTradingDay
//            @JsonProperty("08. previous close") String previousClose,
//            @JsonProperty("09. change") String change,
//            @JsonProperty("10. change percent") String changePercent
    ){}
}
