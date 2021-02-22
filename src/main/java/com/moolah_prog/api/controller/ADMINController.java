package com.moolah_prog.api.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.validation.Valid;
import org.springframework.validation.annotation.Validated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import com.moolah_prog.api.exception.ResourceNotFoundException;
import com.moolah_prog.api.model.AuthRequest;
import com.moolah_prog.api.model.User;
import com.moolah_prog.api.model.moolahUser;
import com.moolah_prog.api.repository.moolahUserRepository;
import com.moolah_prog.api.util.JwtUtil;
//@CrossOrigin(allowedHeaders = "Access-Control-Allow-Methods",origins = "http://localhost:4200")
@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class ADMINController {

	@Autowired
	private JwtUtil jwtutil;
	EntityManager em;
	@Autowired
	private moolahUserRepository moolahuserRepository;
	@Autowired
   private AuthenticationManager authenticationManager;
	
	
	@GetMapping("/ADMIN/welcomestring")
	public String welcome()
	{
		return "Welcome to Moolah";

	}
	
	@PostMapping("/authenticate")
	public String generateToken(@RequestBody User user) throws Exception
	{
		try {
			authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(user.getUserName(),user.getPassword()));
		}
		catch(Exception ex)
		{
			throw new Exception("invalid username/password");
		}
		return jwtutil.generateToken(user.getUserName());

	}
	

	
	@GetMapping("/ADMIN/moolahUserList")
	public List<moolahUser> getAllmoolahUsers() {
		return moolahuserRepository.findAll();
	}
	
	

/*@DeleteMapping("/ADMIN/delete/{id}")
	public Map<String, Boolean> delete_moolahUser(@PathVariable(value = "id") String moolahUserId)
			throws ResourceNotFoundException {
		try {
			moolahUser moolahuser = moolahuserRepository.findById(moolahUserId);
			moolahuserRepository.delete(moolahuser);
			Map<String, Boolean> response = new HashMap<>();
			response.put("deleted", Boolean.TRUE);
			return response;
		} catch (NoResultException e) { // else part
			// create a new record.
			new ResourceNotFoundException("User not found for this id : " + moolahUserId);
			return null;
		}
	}*/
	@DeleteMapping("/ADMIN/delete/{id}")
	public Map<String, Boolean> delete_moolahUser(@PathVariable(value = "id") String moolahUserId)
			throws ResourceNotFoundException {
		try {
			moolahUser moolahuser = moolahuserRepository.findById(moolahUserId);
			moolahuserRepository.delete(moolahuser);
			Map<String, Boolean> response = new HashMap<>();
			response.put("deleted", Boolean.TRUE);
			return response;
		} catch (NoResultException e) { // else part
			// create a new record.
			new ResourceNotFoundException("User not found for this id : " + moolahUserId);
			return null;
		}
	}
	@GetMapping("/ADMIN/moolahUsers/{id}")
	public ResponseEntity<moolahUser> get_moolahUserById(@PathVariable(value = "id") String moolahUserId)
			throws ResourceNotFoundException {
		try {
			moolahUser moolahuser = moolahuserRepository.findById(moolahUserId);
			return ResponseEntity.ok().body(moolahuser);

		} catch (NoResultException e) { // else part
			// create a new record.
			new ResourceNotFoundException("User not found for this id : " + moolahUserId);
			return null;
		}
	}

	

}
