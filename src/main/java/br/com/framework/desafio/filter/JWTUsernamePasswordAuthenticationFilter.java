package br.com.framework.desafio.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

public class JWTUsernamePasswordAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
	
	@Value("${spring.security.user.password}")
	private String password;
	
	private static final String LOGIN = "login";
	private static final String TOKEN = "token";

	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {
		User user = (User) authResult.getPrincipal();
		
		String login = user.getUsername();
		
		String token = JWT.create()
				.withClaim(LOGIN, login)
				.sign(Algorithm.HMAC256(password));
		
		Cookie cookie = new Cookie(TOKEN, token);
		cookie.setPath("/");
		cookie.setHttpOnly(true);
		cookie.setMaxAge(60 * 30);
		
		response.addCookie(cookie);
		
		super.successfulAuthentication(request, response, chain, authResult);

	}
}
