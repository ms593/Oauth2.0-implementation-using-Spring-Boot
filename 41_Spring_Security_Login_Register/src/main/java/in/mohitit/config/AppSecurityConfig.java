package in.mohitit.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsPasswordService;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


import lombok.SneakyThrows;

@Configuration
@EnableWebSecurity
public class AppSecurityConfig {
	
	@Autowired
	private UserDetailsService customerService;
	
	
	@Bean
	public BCryptPasswordEncoder pwdEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public DaoAuthenticationProvider authProvider() {
		
		DaoAuthenticationProvider authProvider = new
				        DaoAuthenticationProvider();
		
		
		authProvider.setPasswordEncoder(pwdEncoder());
		authProvider.setUserDetailsService(customerService);
		return authProvider;
	}
	
	@Bean
	@SneakyThrows
	public AuthenticationManager authManager(AuthenticationConfiguration config) {
		return config.getAuthenticationManager();
	}
	
	
	@Bean
	@SneakyThrows
	public SecurityFilterChain security(HttpSecurity http) {
		//logic
		http.authorizeHttpRequests((req)->{
		 req.requestMatchers("/register","/login").permitAll().
		 anyRequest().authenticated();
		});
		
		return http.csrf().disable().build();
	}
	
	
}
