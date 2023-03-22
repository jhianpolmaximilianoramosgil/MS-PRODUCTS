package com.nttdata.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.reactive.function.client.WebClient;

import com.nttdata.model.Customers;

import reactor.core.publisher.Mono;

@Configuration
public class WebClientConfig {
	
	private final WebClient.Builder webClientBuilder = WebClient.builder();
	
	
	public Mono<Customers> getCustomerById(@PathVariable String id){
        return webClientBuilder.build()
                .get()
                .uri("http://localhost:8282/customers/"+id)
                .retrieve()
                .bodyToMono(Customers.class);
    }
	
	
}
