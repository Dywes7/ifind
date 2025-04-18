package br.edu.br.meuprimeirospringboot.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.edu.br.meuprimeirospringboot.entity.Usuario;
import br.edu.br.meuprimeirospringboot.serviceImpl.UsuarioServiceImpl;

@Controller
@RequestMapping("/usuarios")
public class UsuarioController {
	
	@Autowired
	private UsuarioServiceImpl usuario;
	
	@GetMapping("/listar")
	String ListarUsuarios(ModelMap model) {
		model.addAttribute("usuarios", usuario.buscarTodos());
		return "/usuario/lista";
	}
	
	@GetMapping("/cadastrar")
	String CadastrarUsuarios(ModelMap model) {
		model.addAttribute("usuario", new Usuario());
		return "/usuario/cadastro";
	}
	
	@PostMapping("/salvar")
	String Salvar(Usuario u, RedirectAttributes redirectAttributes) {
		try {
			usuario.cadastrar(u);
			redirectAttributes.addFlashAttribute("sucesso", "Usuário cadastrado com sucesso!");
	        return "redirect:/login";
		} catch (RuntimeException e) {
			redirectAttributes.addFlashAttribute("erro", e.getMessage());
	        return "redirect:/usuarios/cadastrar";
		}
	}

}
