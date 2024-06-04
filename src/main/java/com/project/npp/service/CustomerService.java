package com.project.npp.service;

import com.project.npp.entities.Customer;
import com.project.npp.exceptions.CustomerNotFoundException;

public interface CustomerService {

	public Customer addCustomer(Customer customer);

	public Customer getCustomerById(Integer id) throws CustomerNotFoundException;

	public Customer updateCustomer(Customer customer) throws CustomerNotFoundException;
	
	public String deleteCustomerById(Integer id) throws CustomerNotFoundException; 
	
	

}
