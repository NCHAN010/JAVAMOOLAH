package com.moolah_prog.api.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.moolah_prog.api.filter.JwtFilter;
import com.moolah_prog.api.service.CustomUserDetailsService;

//@Configuration
//@EnableWebSecurity
public class  SecurityConfig2 {

/*
	//@Order(3)
	@Configuration
	public static class ADMINWebConfigure_1  extends WebSecurityConfigurerAdapter
	{
		@Autowired
		private JwtFilter jwtFilter;

		@Autowired
		private CustomUserDetailsService userDetailsService;
		@Override
		protected void configure(AuthenticationManagerBuilder auth) throws Exception
		{
			auth.userDetailsService(userDetailsService);
		}
	
		@Bean
		public PasswordEncoder passwordEncoder()
		{
			return NoOpPasswordEncoder.getInstance();
		}
	/*	@Bean
		public BCryptPasswordEncoder passwordEncoder() {
			return new BCryptPasswordEncoder();
		}

		@Bean(name=BeanIds.AUTHENTICATION_MANAGER)
		@Override
		public AuthenticationManager authenticationManagerBean() throws Exception
		{
			return super.authenticationManagerBean();


		}

		@Override 
		protected void configure(HttpSecurity http) throws Exception
		{
			http.cors().disable();
			http.csrf().disable().authorizeRequests().antMatchers("/authenticate","/ADMIN/**","/USER/**","/VISITOR/**")
			.permitAll().antMatchers(HttpMethod.OPTIONS,"/**").permitAll().anyRequest().authenticated()
			.and().exceptionHandling().and().sessionManagement()
			.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		   http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
/*

			http.cors().disable();
			
			http.csrf().disable().authorizeRequests().antMatcher("/ADMIN/**")
			.permitAll().anyRequest().authenticated()
			.and().exceptionHandling().and().sessionManagement()
			.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
	
		//	http.antMatcher("/ADMIN/**").authorizeRequests().anyRequest();*/
	/*		
		}

	}
/*
	
	@Configuration
	@Order(2)
	public static class USERWebConfigure_2  extends WebSecurityConfigurerAdapter
	{
		//@Override 
		protected void configure(HttpSecurity http) throws Exception
		{
			/*http.cors().disable();
			http.csrf().disable().authorizeRequests().antMatchers("/USER/**")
			.permitAll().antMatchers(HttpMethod.OPTIONS,"/**").permitAll().anyRequest().authenticated()
			.and().exceptionHandling().and().sessionManagement()
			.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
	
		/*http.cors().disable();
			http.antMatcher("/USER/**").authorizeRequests().anyRequest().permitAll();

	
			//http.antMatcher("/USER/**").authorizeRequests().anyRequest();
		}
	}
	
	
	@Configuration
	@Order(1)
	public static class VISITORWebConfigure_2  extends WebSecurityConfigurerAdapter
	{
		//@Override 
		protected void configure(HttpSecurity http) throws Exception
		{
			http.cors().disable();
			http.csrf().disable().authorizeRequests().antMatchers("/VISITOR/**")
			.permitAll().antMatchers(HttpMethod.OPTIONS,"/**").permitAll().anyRequest().authenticated()
			.and().exceptionHandling().and().sessionManagement()
			.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		

			//http.cors().disable();
	//	http.antMatcher("/VISITOR/**").authorizeRequests().anyRequest().permitAll();


		//	http.antMatcher("/VISITOR/**").authorizeRequests().anyRequest();
		}
	}


    @Bean
    public UserDetailsService userDetailsService() throws Exception {
        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
        manager.createUser(User
          .withUsername("user")
          .password(encoder().encode("userPass"))
          .roles("USER").build());
        manager.createUser(User
          .withUsername("admin")
          .password(encoder().encode("adminPass"))
          .roles("ADMIN").build());
        return manager;
    }
    
    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }
*/
  /*  
    @Configuration
    @Order(1)
    public static class App1ConfigurationAdapter extends WebSecurityConfigurerAdapter {

        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http.antMatcher("/ADMIN/**").authorizeRequests().anyRequest().permitAll();
        }

       
    }
    
    @Configuration
    @Order(2)
    public static class App2ConfigurationAdapter extends WebSecurityConfigurerAdapter {

    	 @Override
         protected void configure(HttpSecurity http) throws Exception {
             http.antMatcher("/USER/**").authorizeRequests().anyRequest().permitAll();
         }
    }
    
    @Configuration
    @Order(3)
    public static class App3ConfigurationAdapter extends WebSecurityConfigurerAdapter {

        protected void configure(HttpSecurity http) throws Exception {
            http.antMatcher("/VISITOR/**").authorizeRequests().anyRequest().permitAll();
        }
    }*/
}
