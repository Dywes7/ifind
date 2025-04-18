package br.edu.br.meuprimeirospringboot.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.edu.br.meuprimeirospringboot.entity.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
	
	@Query("select u from Usuario u")
	List<Usuario> findAllUsuarios();
	
	@Query("select count(u) from Usuario u")
	int countAllUsuarios();
	
	@Query("select u from Usuario u where u.email = :email")
	Optional<Usuario> findUsuarioByEmailOptional(String email);

	Optional<Usuario> findByUsername(String username);
}
