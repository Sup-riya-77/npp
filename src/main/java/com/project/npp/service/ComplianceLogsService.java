package com.project.npp.service;

import com.project.npp.entities.ComplianceLogs;
import com.project.npp.exceptions.CustomerNotFoundException;
import com.project.npp.exceptions.LogNotFoundException;
import com.project.npp.exceptions.PortRequestNotFoundException;

public interface ComplianceLogsService {
	
	public ComplianceLogs addLog(ComplianceLogs complianceLogs) throws PortRequestNotFoundException, CustomerNotFoundException;
	
	public ComplianceLogs getLog(Integer LogId) throws LogNotFoundException;
	
	public ComplianceLogs UpdateLog(ComplianceLogs complianceLogs) throws LogNotFoundException, PortRequestNotFoundException, CustomerNotFoundException;
	
	

}
