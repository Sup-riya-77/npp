package com.project.npp.service;

import java.util.Optional;

import com.project.npp.entities.ERole;
import com.project.npp.entities.Role;

public interface RoleService {
	
	public Optional<Role> findRoleByName(ERole role);
	
	public Optional<Role> findRoleById(Integer id);

}
