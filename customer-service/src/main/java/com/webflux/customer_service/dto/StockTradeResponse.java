package com.webflux.customer_service.dto;

import com.webflux.customer_service.domain.Ticker;
import com.webflux.customer_service.domain.TradeAction;

public record StockTradeResponse(Integer customerId,
                                 Ticker ticker,
                                 Integer price,
                                 Integer quantity,
                                 TradeAction action,
                                 Integer totalPrice,
                                 Integer balance) {
}
