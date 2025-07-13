package br.edu.br.meuprimeirospringboot.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.edu.br.meuprimeirospringboot.entity.Servico;
import br.edu.br.meuprimeirospringboot.entity.Usuario;
import br.edu.br.meuprimeirospringboot.serviceImpl.ServicoServiceImpl;

@Controller
@RequestMapping("/servicos")
public class ServicoController {
	
	@Autowired
	private ServicoServiceImpl servico;
	
	@GetMapping("/listar")
	String ListarServicos(ModelMap model) {
		model.addAttribute("servicos", servico.buscarTodos());
		return "servico/lista";
	}
	
	@GetMapping("/cadastrar")
	String CadastrarServicos(ModelMap model){
		model.addAttribute("servico",new Servico());
		return "servico/cadastro";
	}
	
	@PostMapping("/salvar")
	String Salvar(Servico s, RedirectAttributes redirectAttributes) {
		
		try {
			
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			Usuario usuarioLogado = (Usuario) auth.getPrincipal();
			
			s.setUsuario(usuarioLogado);
			
			servico.cadastrar(s);
			redirectAttributes.addFlashAttribute("sucesso", "Serviço cadastrado com sucesso!");
			return "redirect:/servicos/listar";
		} catch (RuntimeException e) {
			redirectAttributes.addFlashAttribute("erro", e.getMessage());
	        return "redirect:/servicos/cadastrar";
		}
	}
	
	@GetMapping("/excluir/{id}")
	String excluir(@PathVariable("id") Long id) {
		servico.excluirPorId(id);
		return "redirect:/servicos/listar";	
	}
	
	@GetMapping("/editar/{id}")
	String preEditar(@PathVariable("id") Long id, ModelMap model) {
		model.addAttribute("servico",servico.buscarPorId(id));
		return "servico/cadastro";
	}
	
	@PostMapping("/editar")
	String editar(Servico s, RedirectAttributes redirectAttributes) {
		try {
			servico.editar(s);
			redirectAttributes.addFlashAttribute("sucesso", "Serviço cadastrado com sucesso!");
			return "redirect:/servicos/listar";
		} catch (RuntimeException e) {
			redirectAttributes.addFlashAttribute("erro", e.getMessage());
			return "redirect:/servicos/editar/" + s.getId();
		}
		
			
	}

}
