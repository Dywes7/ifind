package br.edu.br.meuprimeirospringboot.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.ui.ModelMap;
import br.edu.br.meuprimeirospringboot.serviceImpl.AnuncioServiceImpl;
import jakarta.servlet.http.HttpServletRequest;

@Controller
public class HomeController {
	
	@Autowired
	private AnuncioServiceImpl anuncio;
	
	@GetMapping("/")
	String index(ModelMap model) {
		model.addAttribute("anuncios", anuncio.buscarTodos());
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
