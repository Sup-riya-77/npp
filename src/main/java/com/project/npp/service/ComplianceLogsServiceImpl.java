package com.project.npp.service;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.project.npp.repositories.ComplianceLogsRepository;
import com.project.npp.entities.ComplianceLogs;

@Service
public class ComplianceLogsServiceImpl implements ComplianceLogsService {

	@Autowired
	ComplianceLogsRepository repo; 
	
	@Override
	public ComplianceLogs addLog(ComplianceLogs complianceLogs) {
		ComplianceLogs complianceLog=repo.save(complianceLogs);
		return complianceLog;
	}

	@Override
	public ComplianceLogs getLog(Integer LogId) {
		Optional<ComplianceLogs> complianceLog=repo.findById(LogId);
		if(complianceLog.isPresent()) return complianceLog.get();
		return null;
	}

	@Override
	public ComplianceLogs UpdateLog(ComplianceLogs complianceLogs) {
		Optional<ComplianceLogs> complianceLog=repo.findById(complianceLogs.getLogId());
		if(complianceLog.isPresent()) 
			{
			 repo.save(complianceLog.get());
			 return complianceLog.get();
			}
		return null;
	}

}
