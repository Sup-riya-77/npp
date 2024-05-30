package com.project.npp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.npp.entities.Customer;
import com.project.npp.entities.Operator;
import com.project.npp.entities.PortRequest;
import com.project.npp.entities.request.CustomerRequest;
import com.project.npp.entities.request.UpdateCustomerRequest;
import com.project.npp.entities.request.UpdatePortRequest;
import com.project.npp.entities.request.UserPortRequest;
import com.project.npp.service.CustomerService;
import com.project.npp.service.OperatorService;
import com.project.npp.service.PortRequestService;

@RestController
@RequestMapping("/api/customerservice")
public class CustomerServiceController {
	@Autowired
	OperatorService operatorService;
	
	@Autowired
	CustomerService customerService;
	
	@Autowired
	PortRequestService portRequestService; 
	
	@PostMapping("/addcustomer")
	public ResponseEntity<Customer> addCustomer(@RequestBody CustomerRequest customerRequest){
	
		Customer customer=new Customer();
		customer.setName(customerRequest.getName());
		customer.setEmail(customerRequest.getEmail());
		customer.setPhoneNumber(customerRequest.getPhoneNumber());
		Operator currentOperator= operatorService.getOperatorById(customerRequest.getCurrentOperatorId());
		Operator newOperator= operatorService.getOperatorById(customerRequest.getNewOperatorId());
		customer.setCurrentOperator(currentOperator);
		customer.setNewOperator(newOperator);
		Customer cust=customerService.addCustomer(customer);
		return new ResponseEntity<Customer>(cust,HttpStatus.OK);
	}
	
	@PostMapping("/getcustomer")
	public ResponseEntity<Customer> getCustomer(@RequestParam("customerId") Integer customerId){
	
		Customer customer=customerService.getCustomerById(customerId);
		return new ResponseEntity<Customer>(customer,HttpStatus.OK);
	}
	
	@PostMapping("/updatecustomer")
	public ResponseEntity<Customer> updateCustomer(@RequestBody UpdateCustomerRequest updateCustomerRequest){
		Customer customer=new Customer();
		customer.setCustomerId(updateCustomerRequest.getCustomerId());
		customer.setName(updateCustomerRequest.getName());
		customer.setEmail(updateCustomerRequest.getEmail());
		customer.setPhoneNumber(updateCustomerRequest.getPhoneNumber());
		Operator currentOperator= operatorService.getOperatorById(updateCustomerRequest.getCurrentOperatorId());
		Operator newOperator= operatorService.getOperatorById(updateCustomerRequest.getNewOperatorId());
		customer.setCurrentOperator(currentOperator);
		customer.setNewOperator(newOperator);
		Customer cust=customerService.updateCustomer(customer);
		return new ResponseEntity<Customer>(cust,HttpStatus.OK);
	}
	
	@PostMapping("/deletecustomer")
	public ResponseEntity<String> deleteCustomer(@RequestParam("customerId") Integer customerId){
		String message=customerService.deleteCustomerById(customerId);
		return new ResponseEntity<String>(message,HttpStatus.OK);
	}
	
	//**************************************************************************************************
	
	@PostMapping("/submitportrequest")
	public ResponseEntity<PortRequest> submitPortRequest(@RequestBody UserPortRequest userPortRequest)
	{
		PortRequest portRequest= new PortRequest();
		portRequest.setRequestDate(userPortRequest.getRequestDate());
		Customer customer=customerService.getCustomerById(userPortRequest.getCustomerId());
		portRequest.setCustomer(customer);
		PortRequest portReq=portRequestService.addPortRequest(portRequest);
		return new ResponseEntity<PortRequest>(portReq,HttpStatus.OK);
	}
	
	@PostMapping("/getportrequest")
	public ResponseEntity<PortRequest> getPortRequest(@RequestParam("requestId") Integer requestId){
	
		PortRequest portRequest= portRequestService.getPortRequest(requestId);
		return new ResponseEntity<PortRequest>(portRequest,HttpStatus.OK);
	}
	
	@PostMapping("/updateportrequest")
	public ResponseEntity<PortRequest> updatePortRequest(@RequestBody UpdatePortRequest updatePortRequest)
	{
		PortRequest portRequest= new PortRequest();
		portRequest.setRequestId(updatePortRequest.getRequestId());
		portRequest.setRequestDate(updatePortRequest.getRequestDate());
		Customer customer=customerService.getCustomerById(updatePortRequest.getCustomerId());
		portRequest.setCustomer(customer);
		PortRequest portReq=portRequestService.updatePortRequest(portRequest);
		return new ResponseEntity<PortRequest>(portReq,HttpStatus.OK);
	}
	
	@PostMapping("/deleteportrequest")
	public ResponseEntity<String> deletePortRequest(@RequestParam("requestId") Integer requestId){
	
		String message= portRequestService.deletePortRequest(requestId);
		return new ResponseEntity<String>(message,HttpStatus.OK);
	}
	
	
	

}
