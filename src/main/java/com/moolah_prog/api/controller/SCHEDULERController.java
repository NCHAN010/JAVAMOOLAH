package com.moolah_prog.api.controller;

import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import static org.quartz.SimpleScheduleBuilder.simpleSchedule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.quartz.Trigger;
import static org.quartz.TriggerBuilder.newTrigger;
import org.quartz.impl.StdSchedulerFactory;
import static org.quartz.JobBuilder.newJob;

import com.moolah_prog.api.scheduler.ConstantComponentJOB1;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class SCHEDULERController {
	
	
	 @Autowired
	    private Scheduler scheduler;
	 @GetMapping("/schedule")
	 public void schedule() throws SchedulerException
	 {
		 //Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
		   scheduler.start();
		JobDetail ConstantComponentJOB1= newJob(ConstantComponentJOB1.class).withIdentity("ConstantJob","group1").build();
		//Trigger trigger1= TriggerBuilder.newTrigger().withIdentity("cronTrigger1","group1").withSchedule(CronScheduleBuilder.cronSchedule("0 0 1 * * ?")).build();
		Trigger trigger1 = newTrigger().withIdentity("ConstantComponentJOB1","group1").startNow().withSchedule(simpleSchedule().withIntervalInMilliseconds(1000L).repeatForever()).build();
	//	Scheduler scheduler1 = new StdSchedulerFactory().getScheduler();
		
	    scheduler.scheduleJob(ConstantComponentJOB1, trigger1);
		
			
	 }
	 
}
