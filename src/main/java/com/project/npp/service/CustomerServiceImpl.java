package com.project.npp.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.npp.entities.Customer;
import com.project.npp.repositories.CustomerRepository;
@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	CustomerRepository repo;
	@Override
	public Customer addCustomer(Customer customer) {
		Customer cust= repo.save(customer);
		return cust;
	}

	@Override
	public Customer getCustomerById(Integer id) {
		Optional<Customer> cust=repo.findById(id);
		if(cust.isPresent())
		{
			return cust.get();
		}
		return null;
	}

	@Override
	public Customer updateCustomer(Customer customer) {
		Customer cust= repo.save(customer);
		return cust;
	}

}
