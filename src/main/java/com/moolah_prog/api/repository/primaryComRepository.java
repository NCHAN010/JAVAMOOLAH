package com.moolah_prog.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.moolah_prog.api.model.primaryCom;



public interface primaryComRepository extends JpaRepository <primaryCom,Long>{
	
//	@Query("select m from moolahUser m where m.userName = ?1 and m.password =?2")
	//  moolahUser findByUserNameAndPassword(String userName, String password);
	//UPDATE counters SET value = value + 1 WHERE id = 1;
	@Modifying
	@Transactional
	@Query("update primaryCom pc SET pc.no_OfSecCom = pc.no_OfSecCom+1 where pc.orgId=?1  ")
	void incrementSC(String orgId);
	@Query("select pc from primaryCom pc where pc.orgId =?1 and pc.userId =?2")
	primaryCom findBy_org_n_user_ID(String orgId, String userId);
	
	@Query("select pc from primaryCom pc where pc.userId=?2 ")
	List<primaryCom> findBy_user_ID(String userId);
	
	@Query("select  pc.orgId from primaryCom pc where pc.moolahTitle=?1 and pc.userId=?2")
	String findOrgId_By_moolahTitle(String moolahtitle,String userId);
	
	@Query("select pc from primaryCom pc where pc.orgId =?1")
	primaryCom findBy_org(String orgId);
	
}
