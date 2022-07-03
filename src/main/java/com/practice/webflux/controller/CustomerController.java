package com.practice.webflux.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.practice.webflux.dto.Customer;
import com.practice.webflux.service.CustomerService;

import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/customer")
public class CustomerController {
	
	@Autowired
	private CustomerService customerService;
	
	
	@GetMapping
	public List<Customer> getAllCustomers() throws InterruptedException{
		
		return customerService.loadAllCustomers();
		
	}
	
	@RequestMapping(path = "/stream", method = RequestMethod.GET,produces=MediaType.TEXT_EVENT_STREAM_VALUE)
	public Flux<Customer> getAllCustomersStream(){
		
		return customerService.loadAllCustomersStream();
	}

}
