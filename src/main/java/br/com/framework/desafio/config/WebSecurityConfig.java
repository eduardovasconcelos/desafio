package br.com.framework.desafio.config;

import javax.sql.DataSource;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;

import br.com.framework.desafio.filter.JWTBasicAuthenticationFilter;
import br.com.framework.desafio.filter.JWTUsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private DataSource dataSource;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable()
		.authorizeRequests().anyRequest().authenticated()
		.and()
		.formLogin()
//		.loginPage("/login")
        .defaultSuccessUrl("/ok")
//        .failureUrl("/login?error=true")
        .permitAll()
		.and()
		.addFilter(jwtBasicAuthenticationFilter())
		.addFilter(jwtUsernamePasswordAuthenticationFilter())
		.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
		.and()
		.logout()
//		.logoutSuccessUrl("/login?logout=true")
		.permitAll();
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.jdbcAuthentication().dataSource(this.dataSource)
		.usersByUsernameQuery("select login, password, 1 from usuario where login = ?")
		.authoritiesByUsernameQuery("select ?, 'ROLE_USER';");
	}
	
	@Bean
	public JWTUsernamePasswordAuthenticationFilter jwtUsernamePasswordAuthenticationFilter() throws Exception {
		JWTUsernamePasswordAuthenticationFilter jwtFilter = new JWTUsernamePasswordAuthenticationFilter();
		jwtFilter.setAuthenticationManager(authenticationManager());
		
		return jwtFilter;
	}
	
	@Bean
	public JWTBasicAuthenticationFilter jwtBasicAuthenticationFilter() throws Exception {
		JWTBasicAuthenticationFilter jwtBasic = new JWTBasicAuthenticationFilter(authenticationManager());
		return jwtBasic;
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new PasswordEncoder() {
			
			@Override
			public boolean matches(CharSequence rawPassword, String encodedPassword) {
				return DigestUtils.sha1Hex(rawPassword.toString()).equals(encodedPassword);
			}
			
			@Override
			public String encode(CharSequence rawPassword) {
				return DigestUtils.sha1Hex(rawPassword.toString());
			}
		};
	}

}
