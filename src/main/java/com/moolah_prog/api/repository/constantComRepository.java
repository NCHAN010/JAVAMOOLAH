package com.moolah_prog.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.moolah_prog.api.model.constantCom;
import com.moolah_prog.api.model.primaryCom;

public interface constantComRepository extends JpaRepository <constantCom,Long> {

	
	@Query("select cc from constantCom cc where cc.orgId =?1")
	constantCom findBy_org(String orgId);
	
	@Query("select cc from constantCom cc where cc.userId=?1 ")
	List<constantCom> findBy_user_ID(String userId);
}
