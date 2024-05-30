package com.project.npp.service;


import com.project.npp.entities.Operator;


public interface OperatorService {
	
	public Operator addOperator(Operator operator);
	
	public Operator getOperatorById(Integer id);
	
	public Operator updateOperator(Operator operator);
	
	public String deleteOperator(Integer id);
	
	

}
