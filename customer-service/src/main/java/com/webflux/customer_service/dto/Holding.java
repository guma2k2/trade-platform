package com.webflux.customer_service.dto;

import com.webflux.customer_service.domain.Ticker;

public record Holding(Ticker ticker,
                      Integer quantity) {
}
