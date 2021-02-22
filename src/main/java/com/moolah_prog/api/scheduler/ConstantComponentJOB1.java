package com.moolah_prog.api.scheduler;
import java.util.List;

import org.quartz.Job;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;

import com.moolah_prog.api.model.constantCom;
import com.moolah_prog.api.model.moolahUser;
import com.moolah_prog.api.repository.constantComRepository;
import com.moolah_prog.api.repository.moolahUserRepository;
import com.moolah_prog.api.repository.primaryComRepository;

public class ConstantComponentJOB1  implements Job {

	@Autowired
	private constantComRepository constantcomRepository;
	private moolahUserRepository moolahuserRepository;
	
	public ConstantComponentJOB1()
	{
		
	}
	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		// TODO Auto-generated method stub
	    //System.out.println("MyJob is executing!");

		System.out.println("MyJob is executing!");
		

		List<constantCom> allConstantCom = constantcomRepository.findAll();
		Long curr_negative_Val;
		String curr_ID;
	
		for (int i=0;i< allConstantCom.size();i++)
		{
			System.out.println("AAA");
			constantCom curr_constantCom = allConstantCom.get(i);
			System.out.println("Current ID: "+curr_constantCom.getUserId()+"Current Amount: "+curr_constantCom.getConstantAmt());
			curr_negative_Val = curr_constantCom.getConstantAmt();
			curr_ID =  curr_constantCom.getUserId();
			moolahuserRepository.updateConstantComponent(curr_negative_Val,curr_ID);
		
		}
		

        

       
        
	}

}
