package br.com.framework.desafio.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class IndexController {

	@GetMapping(value = "/ok")
	public String index() {
		return "Configuração Inicial";
	}
}
