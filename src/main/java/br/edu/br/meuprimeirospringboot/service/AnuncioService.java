package br.edu.br.meuprimeirospringboot.service;

import java.util.List;

import br.edu.br.meuprimeirospringboot.entity.Anuncio;

public interface AnuncioService {
	
	List<Anuncio> buscarTodos();
	
	Anuncio buscarPorId(Long id);
	
	void excluirPorId(Long id);
	
	Anuncio cadastrar(Anuncio a);
	
	Anuncio editar(Anuncio a);

	List<Anuncio> buscarPorTituloOuDescricao(String termo);
}
