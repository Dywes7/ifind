package br.edu.br.meuprimeirospringboot.service;

import java.util.List;

import br.edu.br.meuprimeirospringboot.entity.Anuncio;

public interface ChatGPTService {
	
	List<Long> buscarAnunciosRelevantes(String consulta, List<Anuncio> anuncios);
	//List<Long> extrairIds(String resposta);
	
	
}
