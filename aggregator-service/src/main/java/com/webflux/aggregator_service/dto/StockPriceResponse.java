package com.webflux.aggregator_service.dto;

import com.webflux.aggregator_service.domain.Ticker;

public record StockPriceResponse(Ticker ticker,
                                 Integer price) {
}