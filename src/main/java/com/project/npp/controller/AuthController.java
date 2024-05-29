package com.project.npp.controller;

import com.project.npp.entities.Operator;
import com.project.npp.entities.Role;
import com.project.npp.entities.UserEntity;
import com.project.npp.security.jwt.JwtUtils;
import com.project.npp.security.payload.request.LoginRequest;
import com.project.npp.security.payload.request.SignupRequest;
import com.project.npp.security.payload.response.JwtResponse;
import com.project.npp.security.payload.response.MessageResponse;
import com.project.npp.security.service.UserDetailsImpl;
import com.project.npp.security.service.UserDetailsServiceImpl;
import com.project.npp.service.OperatorService;
import com.project.npp.service.RoleService;
import com.project.npp.service.UserService;

import jakarta.validation.Valid;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*", maxAge = 3600)
public class AuthController {

	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	JwtUtils jwtUtils;

	@Autowired
	UserDetailsServiceImpl userDetailsService;

	@Autowired
	UserService userService;

	@Autowired
	RoleService roleService;

	@Autowired
	PasswordEncoder passwordEncoder;

	@Autowired
	OperatorService operatorService;

	@PostMapping("/signin")
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtUtils.generateJwtToken(authentication);

		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
		String role = userDetails.getAuthorities().stream().findFirst() // Get the first authority
				.map(item -> item.getAuthority()) // Map it to its authority string
				.orElse(null);

		JwtResponse jwtResponse = new JwtResponse(jwt, userDetails.getId(), userDetails.getUsername(), role,
				userDetails.getOperator());

		return ResponseEntity.ok(jwtResponse);
	}

	@PostMapping("/signup")
	public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
		if (userService.existsByUsername(signUpRequest.getUsername())) {
			return ResponseEntity.badRequest().body(new MessageResponse("Error: Username is already taken!"));
		}

		// Create new user's account
		UserEntity user = new UserEntity();
		user.setUsername(signUpRequest.getUsername());
		user.setPasswordHash(passwordEncoder.encode(signUpRequest.getPassword()));
		Optional<Role> role = roleService.findRoleById(4);
		if (role.isPresent()) {
			user.setRole(role.get());
		}
		Operator operator = operatorService.getOperatorById(signUpRequest.getOperatorId());
		user.setOperator(operator);
		userService.addUserEntity(user);
		return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
	}
	
	
}
