package com.project.npp.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.npp.entities.ComplianceLogs;
import com.project.npp.entities.PortRequest;
import com.project.npp.entities.request.ComplianceLogsRequest;
import com.project.npp.entities.request.UpdateComplianceLogsRequest;
import com.project.npp.exceptions.CustomerNotFoundException;
import com.project.npp.exceptions.LogNotFoundException;
import com.project.npp.exceptions.PortRequestNotFoundException;
import com.project.npp.service.ComplianceLogsService;
import com.project.npp.service.PortRequestService;

@RestController
@RequestMapping("/api/complianceofficer")
public class ComplianceOfficerController {
	@Autowired
	PortRequestService portRequestService;
	
	@Autowired
	ComplianceLogsService complianceLogsService;
	
	@PostMapping("/addlog")
	public ResponseEntity<ComplianceLogs> addLog(@RequestBody ComplianceLogsRequest complianceLogsRequest ) throws CustomerNotFoundException, PortRequestNotFoundException
	{
		ComplianceLogs complianceLogs  = new ComplianceLogs();
		complianceLogs.setCheckPassed(complianceLogsRequest.isCheckPassed());
		complianceLogs.setNotes(complianceLogsRequest.getNotes());
		complianceLogs.setCheckDate(complianceLogsRequest.getCheckDate());
		PortRequest portRequest= portRequestService.getPortRequest(complianceLogsRequest.getPortRequestId());
		complianceLogs.setPortRequest(portRequest);
		ComplianceLogs complianceLog=complianceLogsService.addLog(complianceLogs);
		return new ResponseEntity<ComplianceLogs>(complianceLog,HttpStatus.OK);
	}
	
	@PostMapping("/getlog")
	public ResponseEntity<ComplianceLogs> getLog(@RequestParam("logId") Integer logId ) throws LogNotFoundException
	{
		ComplianceLogs complianceLog=complianceLogsService.getLog(logId);
		return new ResponseEntity<ComplianceLogs>(complianceLog,HttpStatus.OK);
	}
	
	@PostMapping("/updatelog")
	public ResponseEntity<ComplianceLogs> updateLog(@RequestBody UpdateComplianceLogsRequest updateComplianceLogsRequest ) throws CustomerNotFoundException, PortRequestNotFoundException, LogNotFoundException
	{
		ComplianceLogs complianceLogs  = new ComplianceLogs();
		complianceLogs.setLogId(updateComplianceLogsRequest.getLogId());
		complianceLogs.setCheckPassed(updateComplianceLogsRequest.isCheckPassed());
		complianceLogs.setNotes(updateComplianceLogsRequest.getNotes());
		complianceLogs.setCheckDate(updateComplianceLogsRequest.getCheckDate());
		PortRequest portRequest= portRequestService.getPortRequest(updateComplianceLogsRequest.getPortRequestId());
		complianceLogs.setPortRequest(portRequest);
		ComplianceLogs complianceLog=complianceLogsService.UpdateLog(complianceLogs);
		return new ResponseEntity<ComplianceLogs>(complianceLog,HttpStatus.OK);
	}
	
	
}
