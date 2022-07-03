package com.practice.webflux.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.practice.webflux.dao.CustomerDao;
import com.practice.webflux.dto.Customer;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class CustomerHandler {
	
	@Autowired
	private CustomerDao customerDao;
	
	
	public Mono<ServerResponse> getCustomersList(ServerRequest request){
		
		Flux<Customer> customerList = customerDao.getCustomerList();
		
		return ServerResponse.ok().body(customerList, Customer.class);
		
	}
	
	public Mono<ServerResponse> getCustomerById(ServerRequest request){
		
		Integer id=Integer.valueOf(request.pathVariable("input"));
		
		//Mono<Customer> customer = customerDao.getCustomerList().filter(x->x.getId().equals(5)).take(1).single();
		Mono<Customer> customer = customerDao.getCustomerList().filter(x->x.getId().equals(id)).next();
		
		return ServerResponse.ok().body(customer, Customer.class);
		
	}
	
	public Mono<ServerResponse> saveCustomer(ServerRequest request){
		
		Mono<Customer> customer = request.bodyToMono(Customer.class);
		
		Mono<String> customerDetails=customer.map(x->x.getId()+":"+x.getName());
		
		return ServerResponse.ok().body(customerDetails,String.class);
		
	}

}
