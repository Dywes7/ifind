package br.edu.br.meuprimeirospringboot.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.edu.br.meuprimeirospringboot.entity.Servico;
import br.edu.br.meuprimeirospringboot.entity.Usuario;
import br.edu.br.meuprimeirospringboot.entity.Anuncio;
import br.edu.br.meuprimeirospringboot.repository.AnuncioRepository;
import br.edu.br.meuprimeirospringboot.repository.ServicoRepository;

@Controller
@RequestMapping("/perfil")
public class PerfilController {
	
	@Autowired
	private ServicoRepository servico;
	
	@Autowired
	private AnuncioRepository anuncio;

	
	@GetMapping("/usuario")
	String PerfilUsuario(ModelMap model) {
		return null;
	}
	
	@GetMapping("/servicos")
	String PerfilServicos(ModelMap model) {
		
		Usuario usuarioLogado = getUsuarioLogado();
		
		List<Servico> servicosDoUsuario = servico.findByUsuario(usuarioLogado);
		
		model.addAttribute("servicos", servicosDoUsuario);
		return "/perfil/servicos";
	}
	
	@GetMapping("/anuncios")
	String PerfilAnuncios(ModelMap model) {
		Usuario usuarioLogado = getUsuarioLogado();
		
		List<Anuncio> anunciosDoUsuario = anuncio.findAnunciosByUsuario(usuarioLogado);
		
		model.addAttribute("anuncios", anunciosDoUsuario);
		return "/perfil/anuncios";
	}
	
	// Método utilitário para obter o usuário logado
    private Usuario getUsuarioLogado() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return (Usuario) auth.getPrincipal();
    }
	
	
	
	
}
