package com.moolah_prog.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.moolah_prog.api.model.primaryCom;
import com.moolah_prog.api.model.secondaryCom;

public interface secondaryComRepository extends JpaRepository <secondaryCom,Long>{
	
	//@Query("select pc from primaryCom pc where pc.orgId =?1 and pc.userId =?2")
	//primaryCom findBy_org_n_user_ID(String orgId, String userId);
	
	@Query("select sc from secondaryCom sc where sc.orgId=?1 ")
	List<secondaryCom> findBy_orgId(String orgId);
	
	
}
