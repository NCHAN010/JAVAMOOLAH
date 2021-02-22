package com.moolah_prog.api.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.www.BasicAuthenticationEntryPoint;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.moolah_prog.api.filter.JwtFilter;
import com.moolah_prog.api.service.CustomUserDetailsService;

@Configuration
@EnableWebSecurity
public class MultipleEntryPointsSecurityConfig {
	
	
	
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
	    
	    @Bean
	    public static AuthenticationEntryPoint loginUrlauthenticationEntryPoint(){
	        return new LoginUrlAuthenticationEntryPoint("/userLogin");
	    }
	            
	    @Bean
	    public static AuthenticationEntryPoint loginUrlauthenticationEntryPointWithWarning(){
	        return new LoginUrlAuthenticationEntryPoint("/userLoginWithWarning");
	    }

	    @Configuration
	    @Order(1)
	    public static class App1ConfigurationAdapter extends WebSecurityConfigurerAdapter {

	    	@Autowired
			private JwtFilter jwtFilter;

			@Autowired
			private CustomUserDetailsService userDetailsService;
			
			
	    	@Bean(name=BeanIds.AUTHENTICATION_MANAGER)
	    	@Override
	    	public AuthenticationManager authenticationManagerBean() throws Exception
	    	{
	    		return super.authenticationManagerBean();


	    	}
	        @Override
	        protected void configure(HttpSecurity http) throws Exception {
	           /* http.antMatcher("/ADMIN/**")
	                .authorizeRequests().anyRequest().hasRole("ADMIN")
	                .and().httpBasic().authenticationEntryPoint(authenticationEntryPoint());*/
	        	http.cors().disable();
				http.csrf().disable();
				http.antMatcher("/ADMIN/**").authorizeRequests().anyRequest().permitAll()
				.and().exceptionHandling().and().sessionManagement()
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
			   http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
	        }

	     /*   @Bean
	        public AuthenticationEntryPoint authenticationEntryPoint(){
	            BasicAuthenticationEntryPoint entryPoint = 
	              new BasicAuthenticationEntryPoint();
	            entryPoint.setRealmName("admin realm");
	            return entryPoint;
	        }*/
	        
	    }
	    
	    @Configuration
	    @Order(2)
	    public static class App2ConfigurationAdapter extends WebSecurityConfigurerAdapter {

	    /*    protected void configure(HttpSecurity http) throws Exception {
	            http.antMatcher("/USER/**")
	                .authorizeRequests().anyRequest().hasRole("USER")
	                .and()
	                // formLogin configuration
	                
	                .exceptionHandling()
	                .defaultAuthenticationEntryPointFor(
	                  loginUrlauthenticationEntryPointWithWarning(),
	                  new AntPathRequestMatcher("/user/private/**"))
	                .defaultAuthenticationEntryPointFor(
	                  loginUrlauthenticationEntryPoint(), 
	                  new AntPathRequestMatcher("/user/general/**"));
	        }*/
	    	   protected void configure(HttpSecurity http) throws Exception {
	    		   http.cors().disable();
					http.csrf().disable();
		            http.antMatcher("/USER/**").authorizeRequests().anyRequest().permitAll();
		        }
	    	
	    }
	    
	    @Configuration
	    @Order(3)
	    public static class App3ConfigurationAdapter extends WebSecurityConfigurerAdapter {

	        protected void configure(HttpSecurity http) throws Exception {
	        	http.cors().disable();
				http.csrf().disable();
	            http.antMatcher("/VISITOR/**").authorizeRequests().anyRequest().permitAll();
	        }
	    }
}
