package com.project.npp.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.npp.entities.ERole;
import com.project.npp.entities.Role;
import com.project.npp.service.RoleService;
import com.project.npp.service.UserService;

@RestController
@RequestMapping("/api/admin")
public class SystemAdminController {
	
	@Autowired
	UserService userService;
	
	@Autowired
	RoleService roleService;
	
	@PostMapping("/updateuserrole")
	public ResponseEntity<String> updateUserRole(@RequestParam("id") Integer userId,@RequestParam("role") ERole role)
	{
		 Optional<Role> r= roleService.findRoleByName(role);
		String message= userService.updateRole(userId, r.get());
		return new ResponseEntity<String> (message,HttpStatus.OK);
	}
	
	
	

}
