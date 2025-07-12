package br.edu.br.meuprimeirospringboot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.edu.br.meuprimeirospringboot.entity.Anuncio;
import br.edu.br.meuprimeirospringboot.entity.Usuario;

public interface AnuncioRepository extends JpaRepository<Anuncio,Long> {
	
	@Query("select a from Anuncio a")
	List<Anuncio> findAllAnuncios();
	
	@Query("select count(a) from Anuncio a")
	int countAllAnuncios();
	
	@Query("SELECT DISTINCT s.anuncio FROM Servico s WHERE s.usuario = :usuario AND s.anuncio IS NOT NULL")
	List<Anuncio> findAnunciosByUsuario(@Param("usuario") Usuario usuario);
	
	List<Anuncio> findByTituloContainingIgnoreCaseOrDescricaoContainingIgnoreCase(String titulo, String descricao);


}
