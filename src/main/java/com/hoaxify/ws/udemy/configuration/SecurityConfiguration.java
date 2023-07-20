package com.hoaxify.ws.udemy.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.EnableGlobalAuthentication;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true)
public class SecurityConfiguration {
	
	@Autowired
	UserAuthService userAuthService;
	
	@Autowired
	PasswordEncoder passwordEncoder;

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

		http.csrf(crsf -> crsf.disable());

		http.httpBasic((httpbasic) -> httpbasic.authenticationEntryPoint(new AuthEntryPoint()));

		http.authorizeHttpRequests((authorizeHttpRequests) -> authorizeHttpRequests
				.requestMatchers(HttpMethod.POST, "/api/v1.0/auth").authenticated()
				.requestMatchers(HttpMethod.PUT, "/api/v1.0/users/{username}").authenticated()
				.anyRequest().permitAll()
		);

		// session olarak kalmasın
		http.sessionManagement((sessionManagement)->sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
		
		
		return http.build();

	}
	
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
//		auth.authenticationProvider(authProvider());
		auth.userDetailsService(userAuthService).passwordEncoder(passwordEncoder);
		
	}
	
//	public AuthenticationProvider authProvider() {
//		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
//		provider.setUserDetailsService(userAuthService);
//		provider.setPasswordEncoder(passwordEncoder);
//		return provider;
//	}
	
	

}
