package com.project.npp.service;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.project.npp.repositories.ComplianceLogsRepository;
import com.project.npp.entities.ComplianceLogs;
import com.project.npp.entities.PortRequest;
import com.project.npp.exceptions.CustomerNotFoundException;
import com.project.npp.exceptions.LogNotFoundException;
import com.project.npp.exceptions.PortRequestNotFoundException;

@Service
public class ComplianceLogsServiceImpl implements ComplianceLogsService {

	@Autowired
	ComplianceLogsRepository repo; 
	
	@Autowired
	PortRequestService portRequestService;
	
	@Override
	public ComplianceLogs addLog(ComplianceLogs complianceLogs) throws PortRequestNotFoundException, CustomerNotFoundException {
		
		if(complianceLogs.isCheckPassed())
		{
			PortRequest portRequest= portRequestService.getPortRequest(complianceLogs.getPortRequest().getRequestId());
			portRequest.setComplianceChecked(true);
			PortRequest portReq= portRequestService.updatePortRequest(portRequest);
			complianceLogs.setPortRequest(portReq);
			ComplianceLogs complianceLog=repo.save(complianceLogs);
			return complianceLog;
			
		}
		else {
			PortRequest portRequest= portRequestService.getPortRequest(complianceLogs.getPortRequest().getRequestId());
			complianceLogs.setPortRequest(portRequest);
			ComplianceLogs complianceLog=repo.save(complianceLogs);
			return complianceLog;
		}
		
		
	}

	@Override
	public ComplianceLogs getLog(Integer LogId) throws LogNotFoundException{
		Optional<ComplianceLogs> complianceLog=repo.findById(LogId);
		if(complianceLog.isPresent()) return complianceLog.get();
		else throw new  LogNotFoundException("Log with Id "+LogId+" not Fouund");
	}

	@Override
	public ComplianceLogs UpdateLog(ComplianceLogs complianceLogs) throws LogNotFoundException, PortRequestNotFoundException, CustomerNotFoundException {
		Optional<ComplianceLogs> complianceLog=repo.findById(complianceLogs.getLogId());
		if(complianceLog.isPresent()) 
			{
			
			if(complianceLogs.isCheckPassed())
			{
				PortRequest portRequest= portRequestService.getPortRequest(complianceLogs.getPortRequest().getRequestId());
				portRequest.setComplianceChecked(true);
				PortRequest portReq= portRequestService.updatePortRequest(portRequest);
				complianceLogs.setPortRequest(portReq);
			}
			else {
				PortRequest portRequest= portRequestService.getPortRequest(complianceLogs.getPortRequest().getRequestId());
				complianceLogs.setPortRequest(portRequest);
			}
			
			 repo.save(complianceLogs);
			 return complianceLog.get();
			}
		else throw new  LogNotFoundException("Log with Id "+complianceLogs.getLogId()+" not Fouund");
	}

}
