 package com.moolah_prog.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.moolah_prog.api.model.moolahUser;

@Repository
public interface moolahUserRepository  extends JpaRepository<moolahUser, Long>{

	
	 @Query("select m from moolahUser m where m.userName = ?1 and m.password =?2")
	  moolahUser findByUserNameAndPassword(String userName, String password);
	 
	 @Query("select m from moolahUser m where m.userName = ?1")
	  moolahUser findByUserName(String userName);
	 @Query("Select m from moolahUser m where m.userId =?1 ")
	 moolahUser findById(String userId);
	 
	 @Modifying
	 @Transactional
	 @Query("update moolahUser m  SET m.currAmt =m.currAmt+?1 where m.userId = ?2 ")
	 void updateConstantComponent(Long currAmt,String userId);
	 
}
