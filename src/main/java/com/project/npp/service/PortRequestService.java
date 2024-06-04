package com.project.npp.service;

import com.project.npp.entities.PortRequest;
import com.project.npp.exceptions.CustomerNotFoundException;
import com.project.npp.exceptions.PortRequestNotFoundException;

public interface PortRequestService {
	
	public PortRequest addPortRequest(PortRequest portRequest);
	
	public PortRequest getPortRequest(Integer portRequestId) throws PortRequestNotFoundException;
	
	public PortRequest updatePortRequest(PortRequest portRequest)throws CustomerNotFoundException,PortRequestNotFoundException;

	public String deletePortRequest(Integer portRequestId) throws PortRequestNotFoundException;
}
