package br.edu.br.meuprimeirospringboot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.edu.br.meuprimeirospringboot.entity.Anuncio;

public interface AnuncioRepository extends JpaRepository<Anuncio,Long> {
	
	@Query("select a from Anuncio a")
	List<Anuncio> findAllAnuncios();
	
	@Query("select count(a) from Anuncio a")
	int countAllAnuncios();

}
