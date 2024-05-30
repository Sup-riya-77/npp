package com.project.npp.service;

import java.util.Optional;

import com.project.npp.entities.ERole;
import com.project.npp.entities.Role;
import com.project.npp.entities.UserEntity;

public interface UserService {

	public UserEntity addUserEntity(UserEntity user);

	public String updateRole(Integer userId, Role role);

	public Optional<UserEntity> findByUsername(String username);

	public Boolean existsByUsername(String username);

	public Optional<UserEntity> findByRole(ERole role);
}
