package com.tameme.cobranza.front;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.jdbc.JdbcDaoImpl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import static org.springframework.security.config.Customizer.withDefaults;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;

@Configuration
@EnableWebSecurity
public class SeguridadConfig {
	
	private static final String QRY_USERS = 
			"select usuario, contrasena, activo from usuarios where usuario = ?";
	private static final String QRY_ROLES = 
			"select usuario, rol from roles where usuario = ?";

	@Autowired
	private BCryptPasswordEncoder bCrypt;
	
	@Autowired
	private DataSource dataSource;
	
	@Autowired
	private AuthenticationSuccessHandler auth;
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public JdbcDaoImpl users() {
		JdbcDaoImpl srv = new JdbcDaoImpl();
		srv.setDataSource(dataSource);
		srv.setUsersByUsernameQuery(SeguridadConfig.QRY_USERS);
		srv.setAuthoritiesByUsernameQuery(SeguridadConfig.QRY_ROLES);

		return srv;
	}
	
	public void init(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(users()).passwordEncoder(bCrypt);
	}
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http
			.authorizeHttpRequests((authorize) -> authorize
					.antMatchers("/user/**").hasRole("USER")
					.antMatchers("/admin/**").hasRole("ADMIN")
					.antMatchers("/customer/**").hasRole("CUSTOMER")
					.antMatchers("/semantic/**").permitAll()
					.antMatchers("/password/**").permitAll()
				.anyRequest().authenticated()
			)
			.httpBasic(withDefaults())
			.formLogin((form) -> form.loginPage("/login").successHandler(auth).permitAll() )
			.logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout")).logoutSuccessUrl("/login");
		return http.build();
	}

}
