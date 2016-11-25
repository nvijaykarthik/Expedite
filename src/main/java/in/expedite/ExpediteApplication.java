package in.expedite;

import java.security.Principal;

import javax.servlet.Filter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import in.expedite.auth.CreateSessionCookieForWeb;
import in.expedite.auth.JwtAuthenticationFilter;
import in.expedite.auth.JwtAuthenticationProvider;
import in.expedite.auth.JwtAuthenticationSuccessHandler;
import in.expedite.auth.RestAuthenticationEntryPoint;
import in.expedite.service.UserDetailsService;

@SpringBootApplication
@RestController
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class ExpediteApplication extends SpringBootServletInitializer  {

	@RequestMapping("/user")
	public Principal user(Principal user) {
		return user;
	}
	
	public static void main(String[] args) {
		SpringApplication.run(ExpediteApplication.class, args);
	}
	
   @Configuration
   protected static class AuthServer extends WebMvcConfigurerAdapter{
	@Override
	 public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/login").setViewName("login");
	 }
   }
   
	@Configuration
	protected static class LoginConfig extends WebSecurityConfigurerAdapter {

		@Autowired
		UserDetailsService userDetailsService;

		@Autowired
		CreateSessionCookieForWeb createSessionCookieForWeb;
		
		@Override
		protected void configure(HttpSecurity http) throws Exception {
		
			http
				.formLogin()
				.loginPage("/login")
				.defaultSuccessUrl("/index.html", true)
				.failureForwardUrl("/error.html")
				.successHandler(createSessionCookieForWeb)
				.permitAll()
			.and()
				.authorizeRequests()
				.antMatchers("/login","/pages/**").permitAll()
				.anyRequest().authenticated()
			.and()
				.logout().permitAll();
	
		}

		@Override
		protected void configure(AuthenticationManagerBuilder auth) throws Exception {
			 auth.userDetailsService(userDetailsService).passwordEncoder(bcryptEncoder());
		}
		
		@Bean
		public PasswordEncoder bcryptEncoder(){
			return new BCryptPasswordEncoder();
		}
		
	}
	
	@Configuration
	@Order(-1)
	protected static class RestAuthConfig extends WebSecurityConfigurerAdapter {

		@Autowired
		RestAuthenticationEntryPoint restAuthenticationEntryPoint;
		
		@Autowired
		JwtAuthenticationProvider jwtAuthenticationProvider;
		
		@Autowired
		JwtAuthenticationSuccessHandler jwtAuthenticationSuccessHandler;
		
		
		@Override
		protected void configure(HttpSecurity http) throws Exception {
			http
			   .antMatcher("/resource/**")
			   .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
			   .and()
			   .exceptionHandling().authenticationEntryPoint(restAuthenticationEntryPoint)
			   .and()
			   .addFilterAfter(jwtAuthenticationFilter(),BasicAuthenticationFilter.class).csrf().disable();
			   
		}

		@Bean
		@Override
		public AuthenticationManager authenticationManagerBean() throws Exception {
		      return super.authenticationManagerBean();
		}
		
		@Autowired
		@Override
		protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		  auth.authenticationProvider(jwtAuthenticationProvider);
		}
		
		protected Filter jwtAuthenticationFilter() throws Exception {   
	        JwtAuthenticationFilter jwtAuthenticationFilter = new JwtAuthenticationFilter();
	        jwtAuthenticationFilter.setAuthenticationSuccessHandler(jwtAuthenticationSuccessHandler);
	        jwtAuthenticationFilter.setAuthenticationManager(this.authenticationManagerBean());
	        return jwtAuthenticationFilter;
		}
	}

}

