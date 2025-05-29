package com.webflux.customer_service.controller;

import com.webflux.customer_service.dto.CustomerInformation;
import com.webflux.customer_service.dto.StockTradeRequest;
import com.webflux.customer_service.dto.StockTradeResponse;
import com.webflux.customer_service.service.CustomerService;
import com.webflux.customer_service.service.TradeService;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("customers")
public class CustomerController {

    private final CustomerService customerService;
    private final TradeService tradeService;

    public CustomerController(CustomerService customerService, TradeService tradeService) {
        this.customerService = customerService;
        this.tradeService = tradeService;
    }

    @GetMapping("/{customerId}")
    public Mono<CustomerInformation> getCustomerInformation(@PathVariable Integer customerId){
        return this.customerService.getCustomerInformation(customerId);
    }

    @PostMapping("/{customerId}/trade")
    public Mono<StockTradeResponse> trade(@PathVariable Integer customerId, @RequestBody Mono<StockTradeRequest> mono){
        return mono.flatMap(req -> this.tradeService.trade(customerId, req));
    }

}