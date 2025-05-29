package com.webflux.aggregator_service.client;

import com.webflux.aggregator_service.dto.CustomerInformation;
import com.webflux.aggregator_service.dto.StockTradeRequest;
import com.webflux.aggregator_service.dto.StockTradeResponse;
import com.webflux.aggregator_service.exceptions.ApplicationExceptions;
import com.webflux.aggregator_service.exceptions.CustomerNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ProblemDetail;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import org.springframework.web.reactive.function.client.WebClientResponseException.NotFound;
import org.springframework.web.reactive.function.client.WebClientResponseException.BadRequest;

import java.util.Objects;

public class CustomerServiceClient {

    private static final Logger log = LoggerFactory.getLogger(CustomerServiceClient.class);
    private final WebClient client;

    public CustomerServiceClient(WebClient client) {
        this.client = client;
    }


    public Mono<CustomerInformation> getCustomerInformation(Integer customerId) {
        return client.get()
                .uri("/customers/{id}", customerId)
                .retrieve()
                .bodyToMono(CustomerInformation.class)
                .onErrorResume(NotFound.class, ex -> ApplicationExceptions.customerNotFound(customerId));
    }


    public Mono<StockTradeResponse> trade(Integer customerId, StockTradeRequest request) {
        return client.post()
                .uri("/customers/{id}/trade", customerId)
                .bodyValue(request)
                .retrieve()
                .bodyToMono(StockTradeResponse.class)
                .onErrorResume(NotFound.class, ex -> ApplicationExceptions.customerNotFound(customerId))
                .onErrorResume(BadRequest.class, ex -> handleException(ex));
    }

    private <T> Mono<T> handleException(BadRequest exception){
        var pd = exception.getResponseBodyAs(ProblemDetail.class);
        var message = Objects.nonNull(pd) ? pd.getDetail() : exception.getMessage();
        log.error("customer service problem detail: {}", pd);
        return ApplicationExceptions.invalidTradeRequest(message);
    }

}
