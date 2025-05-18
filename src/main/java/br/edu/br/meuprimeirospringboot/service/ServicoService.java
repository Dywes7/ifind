package br.edu.br.meuprimeirospringboot.service;

import java.util.List;

import br.edu.br.meuprimeirospringboot.entity.Servico;

public interface ServicoService {
	
	List<Servico> buscarTodos();
	
	Servico buscarPorId(Long id);
	
	void excluirPorId(Long id);
	
	Servico cadastrar(Servico s);
	
	Servico editar(Servico s);

}
