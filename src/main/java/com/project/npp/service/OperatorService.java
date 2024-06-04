package com.project.npp.service;


import com.project.npp.entities.Operator;
import com.project.npp.exceptions.OperatorNotFoundException;


public interface OperatorService {
	
	public Operator addOperator(Operator operator);
	
	public Operator getOperatorById(Integer id) throws OperatorNotFoundException;
	
	public Operator updateOperator(Operator operator) throws OperatorNotFoundException;
	
	public String deleteOperator(Integer id) throws OperatorNotFoundException;
	
	

}
