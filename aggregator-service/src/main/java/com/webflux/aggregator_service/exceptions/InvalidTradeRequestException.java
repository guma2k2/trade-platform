package com.webflux.aggregator_service.exceptions;

public class InvalidTradeRequestException extends RuntimeException{
    public InvalidTradeRequestException(String message){
        super(message);
    }
}
