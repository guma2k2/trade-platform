package com.webflux.aggregator_service.dto;

import com.webflux.aggregator_service.domain.Ticker;
import com.webflux.aggregator_service.domain.TradeAction;

public record TradeRequest(Ticker ticker,
                           TradeAction action,
                           Integer quantity) {
}