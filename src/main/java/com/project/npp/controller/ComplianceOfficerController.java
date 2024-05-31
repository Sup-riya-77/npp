package com.project.npp.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import com.project.npp.service.ComplianceLogsService;
import com.project.npp.service.PortRequestService;

@RestController
@RequestMapping("/api/complianceofficer")
public class ComplianceOfficerController {
	
	private static final Logger loggers = LoggerFactory.getLogger(ComplianceOfficerController.class);
	@Autowired
	PortRequestService portRequestService;
	
	@Autowired
	ComplianceLogsService complianceLogsService;
	
	@PostMapping("/addlog")
	public ResponseEntity<ComplianceLogs> addLog(@RequestBody ComplianceLogsRequest complianceLogsRequest )
	{
		loggers.info("add log");
		ComplianceLogs complianceLogs  = new ComplianceLogs();
		complianceLogs.setCheckPassed(complianceLogsRequest.isCheckPassed());
		complianceLogs.setNotes(complianceLogsRequest.getNotes());
		complianceLogs.setCheckDate(complianceLogsRequest.getCheckDate());
		if(complianceLogsRequest.isCheckPassed())
		{
			PortRequest portRequest= portRequestService.getPortRequest(complianceLogsRequest.getPortRequestId());
			portRequest.setComplianceChecked(true);
			PortRequest portReq= portRequestService.updatePortRequest(portRequest);
			complianceLogs.setPortRequest(portReq);
			ComplianceLogs complianceLog=complianceLogsService.addLog(complianceLogs);
			return new ResponseEntity<ComplianceLogs>(complianceLog,HttpStatus.OK);
		}
		else {
			PortRequest portRequest= portRequestService.getPortRequest(complianceLogsRequest.getPortRequestId());
			complianceLogs.setPortRequest(portRequest);
			ComplianceLogs complianceLog=complianceLogsService.addLog(complianceLogs);
			loggers.info("log added");
			return new ResponseEntity<ComplianceLogs>(complianceLog,HttpStatus.OK);
		}
	}
	
	@PostMapping("/getlog")
	public ResponseEntity<ComplianceLogs> getLog(@RequestParam("logId") Integer logId )
	{
		ComplianceLogs complianceLog=complianceLogsService.getLog(logId);
		return new ResponseEntity<ComplianceLogs>(complianceLog,HttpStatus.OK);
	}
	
	@PostMapping("/updatelog")
	public ResponseEntity<ComplianceLogs> updateLog(@RequestBody UpdateComplianceLogsRequest updateComplianceLogsRequest )
	{
		ComplianceLogs complianceLogs  = new ComplianceLogs();
		complianceLogs.setLogId(updateComplianceLogsRequest.getLogId());
		complianceLogs.setCheckPassed(updateComplianceLogsRequest.isCheckPassed());
		complianceLogs.setNotes(updateComplianceLogsRequest.getNotes());
		complianceLogs.setCheckDate(updateComplianceLogsRequest.getCheckDate());
		if(updateComplianceLogsRequest.isCheckPassed())
		{
			PortRequest portRequest= portRequestService.getPortRequest(updateComplianceLogsRequest.getPortRequestId());
			portRequest.setComplianceChecked(true);
			PortRequest portReq= portRequestService.updatePortRequest(portRequest);
			complianceLogs.setPortRequest(portReq);
		}
		else {
			PortRequest portRequest= portRequestService.getPortRequest(updateComplianceLogsRequest.getPortRequestId());
			complianceLogs.setPortRequest(portRequest);
		}
		ComplianceLogs complianceLog=complianceLogsService.UpdateLog(complianceLogs);
		return new ResponseEntity<ComplianceLogs>(complianceLog,HttpStatus.OK);
	}
	
	
}
