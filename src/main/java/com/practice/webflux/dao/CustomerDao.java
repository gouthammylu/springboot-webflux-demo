package com.practice.webflux.dao;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.practice.webflux.dto.Customer;
import com.practice.webflux.util.PracticeUtils;

import reactor.core.publisher.Flux;

@Component
public class CustomerDao {
	
	public List<Customer> getCustomers() throws InterruptedException{
		
		List<Customer> list=new ArrayList<>();
		
		for(int i=1;i<=10;i++) {
			Thread.sleep(1000);
			Customer cust=new Customer();
			cust.setId(i);
			cust.setName(PracticeUtils.faker().name().firstName());
			list.add(cust);
		}
		
		return list;
	}
	
	public Flux<Customer> getCustomersStream(){
		
		return  Flux.range(1, 10)
				.delayElements(Duration.ofSeconds(1))
				.doOnNext(x->System.out.println("processing count in stream:"+x))
				.map(x->new Customer(x,PracticeUtils.faker().name().firstName()));
	}
	
	public Flux<Customer> getCustomerList(){
		
		return  Flux.range(1, 10)
				.doOnNext(x->System.out.println("processing count in stream:"+x))
				.map(x->new Customer(x,PracticeUtils.faker().name().firstName()));
	}

}
