package io.github.prluciohermano.rest.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class IndexController {
	
	@RequestMapping(method = RequestMethod.GET, path = "/")
	public String inicio() {
	 return "index.html";	
	}
	
	@RequestMapping(method = RequestMethod.GET, path = "/login")
	public String logar() {
	 return "login.html";	
	}
	
	@RequestMapping(method = RequestMethod.GET, path = "/logout")
	public String logout() {
	 return "login?logout=true";	
	}
	
	@RequestMapping(method = RequestMethod.GET, path = "/arearestrita")
	public String arearestrita() {
		System.out.println("Entrou em Área Restrita");
	 return "pages/arearestrita.html";	
	}
	
	@RequestMapping(method = RequestMethod.GET, path = "/pessoas")
	public String pessoas() {
		System.out.println("Entrou em pessoas");
	 return "pages/pessoas.html";	
	}
	
	@RequestMapping(method = RequestMethod.GET, path = "/listapessoas")
	public String listaPessoas() {
		System.out.println("Entrou em Lista Pessoas");
	 return "pages/listapessoas.html";	
	}
}
