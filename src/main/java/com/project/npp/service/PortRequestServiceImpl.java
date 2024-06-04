package com.project.npp.service;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.npp.entities.Customer;
import com.project.npp.entities.PortRequest;
import com.project.npp.entities.Status;
import com.project.npp.exceptions.CustomerNotFoundException;
import com.project.npp.exceptions.PortRequestNotFoundException;
import com.project.npp.repositories.PortRequestRepository;

@Service
public class PortRequestServiceImpl implements PortRequestService {

	@Autowired
	PortRequestRepository repo;
	@Autowired
	CustomerService customerService;
	@Override
	public PortRequest addPortRequest(PortRequest portRequest) {
		portRequest.setComplianceChecked(false);
		portRequest.setApprovalStatus(Status.PENDING);
		portRequest.setCompletionDate(null);
		PortRequest portReq= repo.save(portRequest);
		return portReq;
	}

	@Override
	public PortRequest getPortRequest(Integer portRequestId)throws PortRequestNotFoundException {
		Optional<PortRequest> portReq=repo.findById(portRequestId);
		if(portReq.isPresent())
		{
			return portReq.get();
		}
		throw new  PortRequestNotFoundException("Port Request with id "+portRequestId+" not found");
	}

	@Override
	public PortRequest updatePortRequest(PortRequest portRequest) throws CustomerNotFoundException, PortRequestNotFoundException {
		Optional<PortRequest> p=repo.findById(portRequest.getRequestId());
		if(p.isPresent())
		{
		   if(portRequest.getComplianceChecked())
		      {
			portRequest.setApprovalStatus(Status.COMPLETED);
			portRequest.setCompletionDate(LocalDate.now());
			Customer customer= customerService.getCustomerById(portRequest.getCustomer().getCustomerId());
			customer.setStatus(Status.COMPLETED);
			customerService.updateCustomer(customer);
			PortRequest portReq= repo.save(portRequest);
			return portReq;
		}
		else 
		{
			portRequest.setApprovalStatus(Status.PENDING);
		    portRequest.setCompletionDate(null);
		    PortRequest portReq= repo.save(portRequest);
			return portReq;
		}
		
		}
		else throw new  PortRequestNotFoundException("Port Request with id "+portRequest.getRequestId()+" not found");
	}

	@Override
	public String deletePortRequest(Integer portRequestId) throws PortRequestNotFoundException {
		Optional<PortRequest> portReq=repo.findById(portRequestId);
		if(portReq.isPresent())
		{
			repo.deleteById(portRequestId);
			return "Deleted Successfully!!";
		}
		throw new  PortRequestNotFoundException("Port Request with id "+portRequestId+" not found");
	}

}
