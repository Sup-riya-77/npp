package com.project.npp.service;

import java.util.Optional;

import com.project.npp.entities.ERole;
import com.project.npp.entities.Role;
import com.project.npp.exceptions.RoleNotFoundException;

public interface RoleService {
	
	public Optional<Role> findRoleByName(ERole role) throws RoleNotFoundException;
	
	public Optional<Role> findRoleById(Integer id) throws RoleNotFoundException;

}
