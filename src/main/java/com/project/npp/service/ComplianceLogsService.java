package com.project.npp.service;

import com.project.npp.entities.ComplianceLogs;

public interface ComplianceLogsService {
	
	public ComplianceLogs addLog(ComplianceLogs complianceLogs);
	
	public ComplianceLogs getLog(Integer LogId);
	
	public ComplianceLogs UpdateLog(ComplianceLogs complianceLogs);
	
	

}
