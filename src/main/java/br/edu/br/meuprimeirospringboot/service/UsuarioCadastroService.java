package br.edu.br.meuprimeirospringboot.service;

import br.edu.br.meuprimeirospringboot.entity.Role;
import br.edu.br.meuprimeirospringboot.entity.Usuario;

public interface UsuarioCadastroService {
    Usuario cadastrar(Usuario u);
    Usuario editar(Usuario u);
    void save(Usuario usuario);
    void criarUsuario(String username, String password, Role role);
} 