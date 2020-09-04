package br.com.framework.desafio.filter;

import java.io.IOException;
import java.util.List;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.util.WebUtils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;

public class JWTBasicAuthenticationFilter extends BasicAuthenticationFilter {
	
	@Value("${spring.security.user.password}")
	private String role;
	
	private static final String LOGIN = "login";
	private static final String TOKEN = "token";

	public JWTBasicAuthenticationFilter(AuthenticationManager authenticationManager) {
		super(authenticationManager);
	}
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		try {			
			Cookie token = WebUtils.getCookie(request, TOKEN);
			
			if (token == null) {
				chain.doFilter(request, response);
				return;
			}
			
			String jwt = token.getValue();
			
			DecodedJWT decodedJwt = JWT.require(Algorithm.HMAC256(LOGIN)).build().verify(jwt);
			String login = decodedJwt.getClaim(LOGIN).asString();	
			
			List<GrantedAuthority> authorities = List.of(new SimpleGrantedAuthority(role));
			UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(login, null, authorities);
			SecurityContextHolder.getContext().setAuthentication(authentication);
			
			chain.doFilter(request, response);
		} catch (JWTVerificationException e) {
			response.sendError(HttpStatus.UNAUTHORIZED.value());
			return;
		}
	}

}
