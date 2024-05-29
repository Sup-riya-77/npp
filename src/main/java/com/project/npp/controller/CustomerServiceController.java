package com.project.npp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.npp.entities.Customer;
import com.project.npp.entities.Operator;
import com.project.npp.entities.request.CustomerRequest;
import com.project.npp.service.CustomerService;
import com.project.npp.service.OperatorService;

@RestController
@RequestMapping("/customerservice")
public class CustomerServiceController {
	@Autowired
	OperatorService operatorService;
	
	@Autowired
	CustomerService customerService;
	
	@PostMapping("/addcustomer")
	public ResponseEntity<Customer> addCustomer(@RequestBody CustomerRequest customerRequest){
	
		Customer customer=new Customer();
		customer.setName(customerRequest.getName());
		customer.setEmail(customerRequest.getEmail());
		customer.setPhoneNumber(customerRequest.getPhoneNumber());
		customer.setStatus(customerRequest.getStatus());
		Operator currentOperator= operatorService.getOperatorById(customerRequest.getCurrentOperatorId());
		Operator newOperator= operatorService.getOperatorById(customerRequest.getNewOperatorId());
		customer.setCurrentOperator(currentOperator);
		customer.setNewOperator(newOperator);
		Customer cust=customerService.addCustomer(customer);
		return new ResponseEntity<Customer>(cust,HttpStatus.OK);
		
		
	}

}
