package com.project.npp.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.npp.entities.Operator;
import com.project.npp.repositories.OperatorRepository;
@Service
public class OperatorServiceImpl implements OperatorService {

	@Autowired
	OperatorRepository repo;
	
	@Override
	public Operator getOperatorById(Integer id) {
		 Optional<Operator> operator= repo.findById(id);
		 if(operator.isPresent()) return operator.get();
		  return null;
	}

}
