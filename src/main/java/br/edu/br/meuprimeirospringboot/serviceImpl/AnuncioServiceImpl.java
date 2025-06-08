package br.edu.br.meuprimeirospringboot.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.edu.br.meuprimeirospringboot.entity.Servico;

import br.edu.br.meuprimeirospringboot.entity.Anuncio;
import br.edu.br.meuprimeirospringboot.repository.AnuncioRepository;
import br.edu.br.meuprimeirospringboot.service.AnuncioService;

@Service
public class AnuncioServiceImpl implements AnuncioService{
	
	@Autowired
	private AnuncioRepository anuncio;

	@Override
	public List<Anuncio> buscarTodos() {
		return anuncio.findAllAnuncios();
	}

	@Override
	public Anuncio buscarPorId(Long id) {
		return anuncio.findById(id).orElseThrow(() -> new IllegalArgumentException("Anuncio " + id + " não encontrado"));
	}

	@Override
	public void excluirPorId(Long id) {
		
		Anuncio a = this.buscarPorId(id);
		
		if (a.getServicos() != null) {
		    for (Servico s : a.getServicos()) {
		        s.setAnuncio(null);
		    }
		}
		
		anuncio.deleteById(id);
		
	}

	@Override
	public Anuncio cadastrar(Anuncio a) {
		
		return anuncio.save(a);
	}

	@Override
	public Anuncio editar(Anuncio a) {
		
		Anuncio a1 = this.buscarPorId(a.getId());
		
		a1.setTitulo(a.getTitulo());
		a1.setDescricao(a.getDescricao());
		a1.setLocalizacao(a.getLocalizacao());
		a1.setCategoria(a.getCategoria());
		
		return anuncio.save(a1);
		
	}
	
	

}
