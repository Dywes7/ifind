package br.edu.br.meuprimeirospringboot.controllers;

import java.time.LocalDateTime;
import java.util.List;

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

import br.edu.br.meuprimeirospringboot.entity.Anuncio;
import br.edu.br.meuprimeirospringboot.entity.Servico;
import br.edu.br.meuprimeirospringboot.entity.Usuario;
import br.edu.br.meuprimeirospringboot.repository.ServicoRepository;
import br.edu.br.meuprimeirospringboot.serviceImpl.AnuncioServiceImpl;


@Controller
@RequestMapping("/anuncios")
public class AnuncioController {
	
	@Autowired
	private AnuncioServiceImpl anuncio;
	
	@Autowired
	private ServicoRepository servico;
	
	@GetMapping("/listar")
	String ListarAnuncios(ModelMap model){
		model.addAttribute("anuncios", anuncio.buscarTodos());
		return "/anuncio/lista";
	}	
	
	@GetMapping("/cadastrar")
	String CadastrarAnuncios(ModelMap model){
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		Usuario usuarioLogado = (Usuario) auth.getPrincipal();
		
		List<Servico> servicosDoUsuario = servico.findByUsuario(usuarioLogado);
		
		model.addAttribute("servicosuser", servicosDoUsuario);
		model.addAttribute("anuncio", new Anuncio());
		
		return "/anuncio/cadastro";
	}
	
	@PostMapping("/salvar")
	String Salvar(Anuncio a, RedirectAttributes redirectAttributes) {
		try {
			
			a.setDataCriacao(LocalDateTime.now());
			
			if (a.getServicos() != null) {
	            for (Servico s : a.getServicos()) {
	                s.setAnuncio(a);
	            }
	        }
			
	        anuncio.cadastrar(a);
	        redirectAttributes.addFlashAttribute("sucesso", "Anuncio cadastrado com sucesso!");
	        return "redirect:/anuncios/listar";
	    } catch (RuntimeException e) {
	        redirectAttributes.addFlashAttribute("erro", e.getMessage());
	        return "redirect:/anuncios/cadastrar";
	    }
	}
	
	@GetMapping("/excluir/{id}")
	String excluir(@PathVariable("id") Long id) {
		anuncio.excluirPorId(id);
		return "redirect:/anuncios/listar";	
	}
	
	@GetMapping("/editar/{id}")
	String preEditar(@PathVariable("id") Long id, ModelMap model) {
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		Usuario usuarioLogado = (Usuario) auth.getPrincipal();
		
		List<Servico> servicosDoUsuario = servico.findByUsuario(usuarioLogado);
		
		model.addAttribute("servicosuser", servicosDoUsuario);
		
		model.addAttribute("anuncio", anuncio.buscarPorId(id));
		return "/anuncio/cadastro";
	}
	
	@PostMapping("/editar")
	String editar(Anuncio a, RedirectAttributes redirectAttributes) {
		try {
			
			Anuncio existente = anuncio.buscarPorId(a.getId());

	        // Desvincular serviços antigos
	        List<Servico> todosDoUsuario = servico.findByUsuario(existente.getServicos().get(0).getUsuario()); // ou buscar via autenticado
	        for (Servico s : todosDoUsuario) {
	            if (s.getAnuncio() != null && s.getAnuncio().getId().equals(existente.getId())) {
	                s.setAnuncio(null); // remove a referência antiga
	            }
	        }
	        
	        existente.setTitulo(a.getTitulo());
	        existente.setDescricao(a.getDescricao());
	        existente.setCategoria(a.getCategoria());
	        existente.setLocalizacao(a.getLocalizacao());
	        
	        if (a.getServicos() != null) {
	            for (Servico s : a.getServicos()) {
	                s.setAnuncio(existente);
	            }
	            existente.setServicos(a.getServicos());
	        }
			
			anuncio.editar(existente);
			
			redirectAttributes.addFlashAttribute("sucesso", "Anuncio cadastrado com sucesso!");
			return "redirect:/anuncios/listar";
		} catch (RuntimeException e) {
			redirectAttributes.addFlashAttribute("erro", e.getMessage());
			return "redirect:/anuncios/editar/" + a.getId();
		}
		
			
	}

}
