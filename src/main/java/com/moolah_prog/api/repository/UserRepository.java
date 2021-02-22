package com.moolah_prog.api.repository;
import com.moolah_prog.api.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
public interface UserRepository extends JpaRepository <User,Integer>{

	
	User findByUserName(String username);
	
}
