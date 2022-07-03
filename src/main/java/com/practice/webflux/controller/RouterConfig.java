package com.practice.webflux.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.practice.webflux.handler.CustomStreamHandler;
import com.practice.webflux.handler.CustomerHandler;

@Configuration
public class RouterConfig {
	
	@Autowired
	private CustomerHandler customerHandler;
	
	@Autowired
	private CustomStreamHandler customStreamHandler;
	
	
	@Bean
	public RouterFunction<ServerResponse> getCustomers(){
		
		return RouterFunctions.route()
				.GET("router/customer",customerHandler::getCustomersList)
				.GET("router/customer/stream",customStreamHandler::getAllCustomers)
				.GET("router/customer/{input}",customerHandler::getCustomerById)
				.POST("router/customer/save",customerHandler::saveCustomer)
				.build();
		
	}

}
