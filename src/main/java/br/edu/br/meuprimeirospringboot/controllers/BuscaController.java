package br.edu.br.meuprimeirospringboot.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import br.edu.br.meuprimeirospringboot.entity.Anuncio;
import br.edu.br.meuprimeirospringboot.repository.AnuncioRepository;
import br.edu.br.meuprimeirospringboot.service.AnuncioService;
import br.edu.br.meuprimeirospringboot.serviceImpl.ChatGPTServiceImpl;

@Controller
public class BuscaController {
	
	@Autowired
	private AnuncioService anuncio;
	
	@Autowired
    private AnuncioRepository anuncioRepository;
	
	@Autowired
	private ChatGPTServiceImpl chatGPTService;
	
	@GetMapping("/buscar")
    public String buscar(@RequestParam("q") String termoBusca, Model model) {
        // Recupera todos os anúncios disponíveis
        List<Anuncio> todosAnuncios = anuncioRepository.findAll();

        // Envia para a IA e obtém os IDs dos mais relevantes
        List<Long> idsRelevantes = chatGPTService.buscarAnunciosRelevantes(termoBusca, todosAnuncios);

        // Busca os anúncios correspondentes aos IDs retornados pela IA
        List<Anuncio> anunciosFiltrados = anuncioRepository.findAllById(idsRelevantes);

        // Envia os dados para a view
        model.addAttribute("termoBusca", termoBusca);
        model.addAttribute("anuncios", anunciosFiltrados);

        return "busca"; // nome da view HTML que renderiza os resultados
    }

}
