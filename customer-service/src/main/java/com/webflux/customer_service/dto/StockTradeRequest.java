package com.webflux.customer_service.dto;


import com.webflux.customer_service.domain.Ticker;
import com.webflux.customer_service.domain.TradeAction;

public record StockTradeRequest(Ticker ticker,
                                Integer price,
                                Integer quantity,
                                TradeAction action) {

    public Integer totalPrice(){
        return price * quantity;
    }

}