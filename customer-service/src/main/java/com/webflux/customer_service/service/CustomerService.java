package com.webflux.customer_service.service;

import com.webflux.customer_service.dto.CustomerInformation;
import com.webflux.customer_service.entity.Customer;
import com.webflux.customer_service.exceptions.ApplicationExceptions;
import com.webflux.customer_service.mapper.EntityDtoMapper;
import com.webflux.customer_service.repository.CustomerRepository;
import com.webflux.customer_service.repository.PortfolioItemRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final PortfolioItemRepository portfolioItemRepository;

    public CustomerService(CustomerRepository customerRepository, PortfolioItemRepository portfolioItemRepository) {
        this.customerRepository = customerRepository;
        this.portfolioItemRepository = portfolioItemRepository;
    }


    public Mono<CustomerInformation> getCustomerInformation(Integer customerId) {
        return customerRepository.findById(customerId)
                .switchIfEmpty(ApplicationExceptions.customerNotFound(customerId))
                .flatMap(this::buildCustomerInformation);
    }

    private Mono<CustomerInformation> buildCustomerInformation(Customer customer) {
        return portfolioItemRepository.findAllByCustomerId(customer.getId())
                .collectList()
                .map(list -> EntityDtoMapper.toCustomerInformation(customer, list));
    }
}
