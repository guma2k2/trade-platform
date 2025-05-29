package com.webflux.aggregator_service.dto;

import com.webflux.aggregator_service.domain.Ticker;

import java.time.LocalDateTime;

public record PriceUpdate(Ticker ticker,
                          Integer price,
                          LocalDateTime time) {
}