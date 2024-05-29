package com.project.npp.repositories;

import org.springframework.data.repository.CrudRepository;

import com.project.npp.entities.Customer;

public interface CustomerRepository extends CrudRepository<Customer, Integer>{

}
