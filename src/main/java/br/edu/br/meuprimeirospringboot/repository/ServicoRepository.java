package br.edu.br.meuprimeirospringboot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.edu.br.meuprimeirospringboot.entity.Servico;
import br.edu.br.meuprimeirospringboot.entity.Usuario;

public interface ServicoRepository extends JpaRepository<Servico,Long>{
	
	
	@Query("select s from Servico s")
	List<Servico> findAllServicos();
	
	@Query("select count(s) from Servico s")
	int countAllServicos();
	
	List<Servico> findByUsuario(Usuario usuario);
	
}
