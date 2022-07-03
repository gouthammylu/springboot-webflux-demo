package com.practice.webflux.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.practice.webflux.dao.CustomerDao;
import com.practice.webflux.dto.Customer;

import reactor.core.publisher.Mono;

@Service
public class CustomStreamHandler {
	
	@Autowired
	private CustomerDao customerDao;

	
	public Mono<ServerResponse> getAllCustomers(ServerRequest request){
		
		return ServerResponse.ok()
				.contentType(MediaType.TEXT_EVENT_STREAM)
				.body(customerDao.getCustomersStream(), Customer.class);
		
	}
}
