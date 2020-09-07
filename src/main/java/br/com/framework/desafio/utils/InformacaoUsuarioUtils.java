package br.com.framework.desafio.utils;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class InformacaoUsuarioUtils {

	private InformacaoUsuarioUtils() {}
	
	public static String getNameUser() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		return authentication.getName();
	}
}
