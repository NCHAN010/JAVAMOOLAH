package com.moolah_prog.api;


import java.util.List;

import javax.annotation.PostConstruct;

import org.quartz.CronScheduleBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import static org.quartz.SimpleScheduleBuilder.simpleSchedule;
import com.moolah_prog.api.model.User;
import com.moolah_prog.api.repository.UserRepository;
import com.moolah_prog.api.scheduler.ConstantComponentJOB1;

import java.util.stream.Collectors;
import java.util.stream.Stream;
@SpringBootApplication
@RestController
@CrossOrigin(origins="*")

@EnableWebMvc
public class MoolahSession2Application  {

	
	public static void main(String[] args) {
		SpringApplication.run(MoolahSession2Application.class, args);
		
		
	/*	try
		{
			   Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
			   scheduler.start();
			JobDetail ConstantComponentJOB1= JobBuilder.newJob(ConstantComponentJOB1.class).withIdentity("ConstantJob","group1").build();
			//Trigger trigger1= TriggerBuilder.newTrigger().withIdentity("cronTrigger1","group1").withSchedule(CronScheduleBuilder.cronSchedule("0 0 1 * * ?")).build();
			Trigger trigger1 = TriggerBuilder.newTrigger().withIdentity("ConstantComponentJOB1","group1").startNow().withSchedule(simpleSchedule().withIntervalInMilliseconds(1000L).repeatForever()).build();
			Scheduler scheduler1 = new StdSchedulerFactory().getScheduler();
			scheduler1.start();
		    scheduler1.scheduleJob(ConstantComponentJOB1, trigger1);
			
				
		}
		catch(Exception e )
		{
			e.printStackTrace();
		}
		
*/
	}
	

	@Bean
	public WebMvcConfigurer corsConfigurer()
	{
		return new WebMvcConfigurer()
		{
			@Override 
			public void addCorsMappings(CorsRegistry registry)
			{

				registry.addMapping("/*").allowedHeaders("*").allowedOrigins("*").allowedMethods("*").allowedOriginPatterns("*");
				
			}

		};
	} 
	@Bean
	public CorsFilter corsFilter() {
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		CorsConfiguration config = new CorsConfiguration();
		//@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600, allowedHeaders = { "x-auth-token", "x-requested-with", "x-xsrf-token" })
		//config.addAllowedOriginPattern("http://localhost:4200/**");
		
		//'Content-Type': 'text/plain',
	    //'Access-Control-Allow-Origin' : '*',
		
		//config.setAllowCredentials(true);
		config.addAllowedOrigin("*");
		config.addAllowedHeader("*");
		config.addAllowedMethod("OPTIONS");
		config.addAllowedMethod("GET");
		config.addAllowedMethod("POST");
		config.addAllowedMethod("PUT");
		config.addAllowedMethod("DELETE");
	
		source.registerCorsConfiguration("/**", config);
		return new CorsFilter(source);
	}
	
	//Scheduler
	
	

}
