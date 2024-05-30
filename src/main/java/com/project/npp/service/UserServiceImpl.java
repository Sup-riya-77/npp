package com.project.npp.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.npp.entities.ERole;
import com.project.npp.entities.Role;
import com.project.npp.entities.UserEntity;
import com.project.npp.repositories.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository repo;
	
	@Override
	public String updateRole(Integer userId, Role role) {
		Optional<UserEntity> user= repo.findById(userId);
		if(user.isPresent())
		{
		  user.get().setRole(role);
		  repo.save(user.get());
		  return "Role Updated Successfully!!!";
		  
		}
		return null;
	}

	@Override
	public UserEntity addUserEntity(UserEntity user) {
		UserEntity userEntity=repo.save(user);
		return userEntity;
	}

	@Override
	public Optional<UserEntity> findByUsername(String username) {
		Optional<UserEntity>  user =repo.findByUsername(username);
		return user;
	}

	@Override
	public Boolean existsByUsername(String username) {
		Boolean b=repo.existsByUsername(username);
		return b;
	}

	@Override
	public Optional<UserEntity> findByRole(ERole role) {
		 Optional<UserEntity>  user =repo.findByRole(role);
		return user;
	}

	
	
	

}
