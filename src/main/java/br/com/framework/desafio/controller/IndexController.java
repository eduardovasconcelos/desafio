package br.com.framework.desafio.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndexController {

	@GetMapping(value = "/ok")
	public String index() {
		return "Configuração Inicial";
	}
}
