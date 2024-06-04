package com.project.npp.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.npp.entities.Operator;
import com.project.npp.exceptions.OperatorNotFoundException;
import com.project.npp.repositories.OperatorRepository;

@Service
public class OperatorServiceImpl implements OperatorService {

	@Autowired
	OperatorRepository repo;

	@Override
	public Operator getOperatorById(Integer id) throws OperatorNotFoundException {
		Optional<Operator> operator = repo.findById(id);
		if (operator.isPresent())
			return operator.get();
	  throw new OperatorNotFoundException("Operator with Id "+id+" NotFound");
	}

	@Override
	public Operator addOperator(Operator operator) {
		Operator op = repo.save(operator);
		return op;
	}

	@Override
	public Operator updateOperator(Operator operator) throws OperatorNotFoundException {
		Optional<Operator> op = repo.findById(operator.getOperatorId());
		if (op.isPresent()) 
		{
		Operator optr= repo.save(op.get());
		return optr;
		}
		throw new OperatorNotFoundException("Operator with Id "+operator.getOperatorId()+" NotFound");
	}

	@Override
	public String deleteOperator(Integer id) throws OperatorNotFoundException{
		 Optional<Operator> operator= repo.findById(id);
		 if(operator.isPresent())
			 {
			 repo.deleteById(id);
			 return "Deleted Successfully!!";
			 }
		 throw new OperatorNotFoundException("Operator with Id "+id+" NotFound");
}

}
