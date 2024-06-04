package com.project.npp.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.npp.entities.ERole;
import com.project.npp.entities.Role;
import com.project.npp.exceptions.RoleNotFoundException;
import com.project.npp.repositories.RoleRepository;

@Service
public class RoleServiceImpl implements RoleService {

	@Autowired
	RoleRepository repo;
	@Override
	public Optional<Role> findRoleByName(ERole role) throws RoleNotFoundException{
		Optional<Role> r= repo.findByName(role);
		return r;
	}
	@Override
	public Optional<Role> findRoleById(Integer id) throws RoleNotFoundException{
		Optional<Role> role= repo.findById(id);
		return role;
	}
	
	

}
