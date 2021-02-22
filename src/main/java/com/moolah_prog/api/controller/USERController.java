package com.moolah_prog.api.controller;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.validation.Valid;

import java.text.ParseException;
import java.text.SimpleDateFormat;  
import java.util.Date;  
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.moolah_prog.api.exception.ResourceNotFoundException;
import com.moolah_prog.api.model.constantCom;
import com.moolah_prog.api.model.moolahUser;
import com.moolah_prog.api.model.primaryCom;
import com.moolah_prog.api.model.secondaryCom;
import com.moolah_prog.api.repository.constantComRepository;
import com.moolah_prog.api.repository.moolahUserRepository;
import com.moolah_prog.api.repository.primaryComRepository;
import com.moolah_prog.api.repository.secondaryComRepository;
import com.moolah_prog.api.util.JwtUtil;
@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class USERController {
	
	@Autowired
	private JwtUtil jwtutil;
	EntityManager em;
	@Autowired
	private moolahUserRepository moolahuserRepository;
	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired 
	primaryComRepository primarycomRepository;
	@Autowired 
	secondaryComRepository secondarycomRepository;
	@Autowired
	constantComRepository constantcomRepository ;
	//&&& Moolah USER 
	@GetMapping("/USER/{username}/{password}/login")
	public ResponseEntity<moolahUser> get_moolahUserByUserNameNPW(@PathVariable(value = "username") String userName,
			@PathVariable(value = "password") String PW) throws ResourceNotFoundException {
		try {
			moolahUser moolahuser = moolahuserRepository.findByUserNameAndPassword(userName, PW);
			return ResponseEntity.ok().body(moolahuser);

		} catch (NoResultException e) { // else part
			// create a new record.
			new ResourceNotFoundException("User not found for this username : " + userName + " and password: " + PW);
			return null;
		}
	}
	@GetMapping("/USER/{userId}/{moolahtitle}/orgId")
	public  String getOrgIdFromPrimaryCom(@PathVariable(value="userId") String userId,@PathVariable(value = "moolahtitle") String moolahtitle)
	throws ResourceNotFoundException
	{
		try
		{
			String primaryC_orgId =primarycomRepository.findOrgId_By_moolahTitle(moolahtitle,userId);
			
			return primaryC_orgId;
		}
		
		catch (NoResultException e) { // else part
			// create a new record.
			new ResourceNotFoundException("orgId  not found for this title : " + moolahtitle );
			return null;
		}
	}

	@GetMapping("/USER/{orgId}/getPrimaryCom")
	public ResponseEntity<primaryCom> get_primaryComByOrgId(@PathVariable(value="orgId") String orgId)throws ResourceNotFoundException {
		try {
			
			primaryCom primaryC =primarycomRepository.findBy_org(orgId);
			System.out.print(primaryC.toString());
			return ResponseEntity.ok().body(primaryC);

		} catch (NoResultException e) { // else part
			// create a new record.
			new ResourceNotFoundException("User not found for this orgID : " + orgId);
			return null;
		}
	}
	@GetMapping("/USER/{orgId}/getconstantCom")
	public ResponseEntity<constantCom> get_constantComByOrgId(@PathVariable(value="orgId") String orgId)throws ResourceNotFoundException {
		try {
			
			constantCom constantC =constantcomRepository.findBy_org(orgId);
			
			return ResponseEntity.ok().body(constantC);

		} catch (NoResultException e) { // else part
			// create a new record.
			new ResourceNotFoundException("User not found for this orgID : " + orgId);
			return null;
		}
	}
	@GetMapping("/USER/{userId}/HomePage")
	public ResponseEntity<moolahUser> get_moolahUserByID(@PathVariable(value = "userId") String userId) throws ResourceNotFoundException {
		try {
			moolahUser moolahuser = moolahuserRepository.findById(userId);
			return ResponseEntity.ok().body(moolahuser);

		} catch (NoResultException e) { // else part
			// create a new record.
			new ResourceNotFoundException("User not found for this ID : " + userId);
			return null;
		}
	}
	//Create
	@GetMapping("/USER/{orgId}/increSC")
	public void increment_PC(@PathVariable(value="orgId")String orgId)throws ResourceNotFoundException {
		try {
		
			System.out.printf(orgId);
			primarycomRepository.incrementSC(orgId);
			
			

		} catch (NoResultException e) { // else part
			// create a new record.
			new ResourceNotFoundException("User not found for this ID : " + orgId);
			
		}
	
	}
	@PostMapping("/USER/{userId}/createPrimaryComponent")
	public @Valid primaryCom createPrimaryComponent(@Valid @RequestBody primaryCom primaryC , @PathVariable(value="userId") String userId)
	{DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSX");
		//primaryC.setDate_created(LocalDateTime.parse(date, formatter););
		return primarycomRepository.save(primaryC);

	}
	//return this.http.post(`http://localhost:8080/moolah/USER/${orgId}/${userId}/createSecondaryComponent`
	@PostMapping("/USER/{orgId}/{userId}/createSecondaryComponent")
	public @Valid secondaryCom createSecondaryComponent(@Valid @RequestBody secondaryCom secondaryC, @PathVariable(value="orgId") String orgId,@PathVariable(value="userId") String userId )
	{
		
		return secondarycomRepository.save(secondaryC);

	}
	@PostMapping("/USER/{userId}/createSecondaryComponent2")
	public @Valid secondaryCom createSecondaryComponent2(@Valid @RequestBody secondaryCom secondaryC, @PathVariable(value="userId") String userId )
	{
		
		return secondarycomRepository.save(secondaryC);

	}
	@PostMapping("/USER/{userId}/createConstantComponent")
	public @Valid constantCom createConstantComponent(@Valid @RequestBody constantCom constantC , @PathVariable(value="userId") String userId )
	{
		//yyyy-MM-dd yyyy-MM-dd
		constantCom curr_Con = constantC;
		String date1= curr_Con.getDYear()+"-"+curr_Con.getDMonth()+"-"+curr_Con.getDDay();
		return constantcomRepository.save(constantC);

	}
	
	//GetMapping
	@GetMapping("/USER/getAllPrimaryCom/{userId}")
	public List<primaryCom> get_primaryComBy_userId(@PathVariable(value = "userId") String userId) throws ResourceNotFoundException {
		try {
			//return primaryComRepository.findBy_user_ID(userId);
			List<primaryCom> primaryCom_ALL = primarycomRepository.findBy_user_ID(userId);
			return primaryCom_ALL;
			

		} catch (NoResultException e) { // else part
			// create a new record.
		//	new ResourceNotFoundException("User not found for this username : " + userName + " and password: " + PW);
		//	return null;
		}
		return null;
	}
	@GetMapping("/USER/userId/getAllSecondaryCom")
	public List<secondaryCom> get_secondaryComBy_orgId(@PathVariable(value = "orgId") String orgId) throws ResourceNotFoundException {
		try {
			//return primaryComRepository.findBy_user_ID(userId);
		
			List<secondaryCom> secondaryCom_ALL =  secondarycomRepository.findBy_orgId(orgId);
			return secondaryCom_ALL;
			

		} catch (NoResultException e) { // else part
			// create a new record.
		//	new ResourceNotFoundException("User not found for this username : " + userName + " and password: " + PW);
		//	return null;
		}
		return null;
	}
	@GetMapping("/USER/userId/getAllConstantCom")
	public List<constantCom> get_constantComBy_orgId(@PathVariable(value = "orgId") String userId) throws ResourceNotFoundException {
		try {
			//return primaryComRepository.findBy_user_ID(userId);
		
			//List<secondaryCom> secondaryCom_ALL =  secondarycomRepository.findBy_orgId(orgId);
			//return secondaryCom_ALL;
			List<constantCom> constantCom_ALL =constantcomRepository.findBy_user_ID(userId);
			return constantCom_ALL;
			
			

		} catch (NoResultException e) { // else part
			// create a new record.
		//	new ResourceNotFoundException("User not found for this username : " + userName + " and password: " + PW);
		//	return null;
		}
		return null;
	}
	//@GetMapping("/USER/{userId}/{moolahtitle}/orgId")
	@GetMapping("/USER/{userId}/{orgId}/getPrimaryCom")
	public ResponseEntity<primaryCom> get_primaryComByuser_org_ID(@PathVariable(value="userId") String userId, @PathVariable(value="orgId")String orgId)
	throws ResourceNotFoundException{
		try {
			primaryCom primaryC = primarycomRepository.findBy_org_n_user_ID(orgId, userId);
			System.out.print(primaryC.toString());
			//return ResponseEntity.ok().body(primaryC);
			return ResponseEntity.ok().body(primaryC);

			
		}
		catch(NoResultException e)
		{
			new ResourceNotFoundException("PrimaryCom not found for this org ID: " +orgId + "and user ID:"+userId);
			return null;
			
			
		}
		
		
	}
	
	
	@GetMapping("/USER/welcomestring")
	public String welcome()
	{
		return "user is working";

	}
	
	


}
