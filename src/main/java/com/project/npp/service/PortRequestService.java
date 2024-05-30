package com.project.npp.service;

import com.project.npp.entities.PortRequest;

public interface PortRequestService {
	
	public PortRequest addPortRequest(PortRequest portRequest);
	
	public PortRequest getPortRequest(Integer portRequestId);
	
	public PortRequest updatePortRequest(PortRequest portRequest);

	public String deletePortRequest(Integer portRequestId);
}
