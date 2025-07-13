package br.edu.br.meuprimeirospringboot.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
		return "usuario/lista";
	}
	
	@GetMapping("/cadastrar")
	String CadastrarUsuarios(ModelMap model) {
		model.addAttribute("usuario", new Usuario());
		return "usuario/cadastro";
	}
	
	@PostMapping("/salvar")
	String Salvar(Usuario u, RedirectAttributes redirectAttributes) {
		try {
			usuario.cadastrar(u);
			redirectAttributes.addFlashAttribute("sucesso", "Usuário cadastrado com sucesso!");
	        return "redirect:/";
		} catch (RuntimeException e) {
			redirectAttributes.addFlashAttribute("erro", e.getMessage());
	        return "redirect:/usuarios/cadastrar";
		}
	}
	
	@GetMapping("/excluir/{id}")
	String excluir(@PathVariable("id") Long id) {
		usuario.excluirPorId(id);
		return "redirect:/usuarios/listar";	
	}
	
	@GetMapping("/editar/{id}")
	String preEditar(@PathVariable("id") Long id, ModelMap model) {
		model.addAttribute("usuario", usuario.buscarPorId(id));
		return "usuario/editar";
	}
	
	@PostMapping("/editar")
	String editar(Usuario a, RedirectAttributes redirectAttributes) {
		try {
			usuario.editar(a);
			redirectAttributes.addFlashAttribute("sucesso", "Usuário editado com sucesso!");
			return "redirect:/usuarios/listar";
		} catch (RuntimeException e) {
			redirectAttributes.addFlashAttribute("erro", e.getMessage());
			return "redirect:/usuarios/editar/" + a.getId();
		}		
	}

}
