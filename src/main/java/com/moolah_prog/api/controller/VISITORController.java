package com.moolah_prog.api.controller;

import javax.persistence.EntityManager;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.moolah_prog.api.model.moolahUser;
import com.moolah_prog.api.repository.moolahUserRepository;
import com.moolah_prog.api.util.JwtUtil;
@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class VISITORController {
	@Autowired
	private JwtUtil jwtutil;
	EntityManager em;
	@Autowired
	private moolahUserRepository moolahuserRepository;
	@Autowired
	private AuthenticationManager authenticationManager;
	@PostMapping("VISITOR/createMoolahUser")
	public moolahUser create_moolahUser(@Valid @RequestBody moolahUser moolahuser) {
		
		
		return moolahuserRepository.save(moolahuser);
	}

	@GetMapping("/VISITOR/welcomestring")
	public String welcome()
	{
		return "visitor is working";

	}
}
