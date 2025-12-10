package com.youkwhan.trade_view.client;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

// @Component
@Service
@RequiredArgsConstructor // from lombok
public class StockClient {

    private final WebClient webClient;

}
