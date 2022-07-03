package com.practice.webflux.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.practice.webflux.dao.CustomerDao;
import com.practice.webflux.dto.Customer;

import reactor.core.publisher.Flux;

@Service
public class CustomerService {
	
	@Autowired
	private CustomerDao customerDao;
	
	public List<Customer> loadAllCustomers() throws InterruptedException{
		
		long start=System.currentTimeMillis();
		
		List<Customer> customers=customerDao.getCustomers();
		
		long end=System.currentTimeMillis();
		
		System.out.println("Total time taken:"+(end-start));
		
		return customers;
	}
	
	public Flux<Customer> loadAllCustomersStream(){
		
		long start=System.currentTimeMillis();
		
		Flux<Customer> customers=customerDao.getCustomersStream();
		
		long end=System.currentTimeMillis();
		
		System.out.println("Total time taken in stream:"+(end-start));
		
		return customers;
	}

}
