package com.webflux.aggregator_service.dto;


import com.webflux.aggregator_service.domain.Ticker;
import com.webflux.aggregator_service.domain.TradeAction;

public record StockTradeRequest(Ticker ticker,
                                Integer price,
                                Integer quantity,
                                TradeAction action) {

    public Integer totalPrice(){
        return price * quantity;
    }

}