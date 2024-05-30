package com.project.npp.service;

import com.project.npp.entities.Customer;

public interface CustomerService {

	public Customer addCustomer(Customer customer);

	public Customer getCustomerById(Integer id);

	public Customer updateCustomer(Customer customer);
	
	public String deleteCustomerById(Integer id);
	
	

}
