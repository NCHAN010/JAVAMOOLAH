package com.moolah_prog.api.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.moolah_prog.api.model.User;
import com.moolah_prog.api.model.moolahUser;
import com.moolah_prog.api.repository.UserRepository;
import com.moolah_prog.api.repository.moolahUserRepository;

@Service
public class CustomMoolahUserDetailsService implements UserDetailsService{

	@Autowired
	private  moolahUserRepository Mrepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		moolahUser moolahuser=Mrepository.findByUserName(username);
		return new org.springframework.security.core.userdetails.User(moolahuser.getUserName() ,moolahuser.getPassword(), new ArrayList<>());
		
	}

}
