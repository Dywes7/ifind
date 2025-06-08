package br.edu.br.meuprimeirospringboot.service;

import java.util.List;
import br.edu.br.meuprimeirospringboot.entity.Usuario;

public interface UsuarioConsultaService {
    Usuario findByUsername(String username);
    List<Usuario> buscarTodos();
    Usuario buscarPorId(Long id);
} 