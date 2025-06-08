package br.edu.br.meuprimeirospringboot.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class HomeController {
	
	@GetMapping("/")
	String index() {
		return "home";
	}
	
	@GetMapping("/login")
	public String loginPage(@RequestParam(value = "error", required = false) String error,
	                        Model model,
	                        HttpServletRequest request) {

	    if (error != null) {
	        model.addAttribute("erro", "Usuário ou senha inválidos.");
	    }

	    // Aqui recupera o flashAttribute do sucesso se existir:
	    Object sucesso = request.getSession().getAttribute("sucesso");
	    if (sucesso != null) {
	        model.addAttribute("sucesso", sucesso.toString());
	        request.getSession().removeAttribute("sucesso"); // <- Remove para evitar exibir infinitamente
	    }

	    return "login";
	}


	
	@GetMapping("/registro")
	public String registroUser() {
		return "registro";
	}

}
