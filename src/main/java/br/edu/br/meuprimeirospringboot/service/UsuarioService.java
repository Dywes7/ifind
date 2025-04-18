package br.edu.br.meuprimeirospringboot.service;

import java.util.List;

import br.edu.br.meuprimeirospringboot.entity.Role;
import br.edu.br.meuprimeirospringboot.entity.Usuario;

public interface UsuarioService {
	Usuario findByUsername(String username);
	
	List<Usuario> buscarTodos();
	
	Usuario buscarPorId(Long id);
	
	void excluirPorId(Long id);
	
	Usuario cadastrar(Usuario u);
	
	Usuario editar(Usuario u);
	
	void save(Usuario usuario);
	
	public void criarUsuario(String username, String password, Role role);

}
