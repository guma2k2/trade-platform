package com.webflux.aggregator_service.dto;


import com.webflux.aggregator_service.domain.Ticker;

public record Holding(Ticker ticker,
                      Integer quantity) {
}
