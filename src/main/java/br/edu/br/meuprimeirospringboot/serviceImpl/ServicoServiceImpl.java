package br.edu.br.meuprimeirospringboot.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.br.meuprimeirospringboot.entity.Servico;
import br.edu.br.meuprimeirospringboot.repository.ServicoRepository;
import br.edu.br.meuprimeirospringboot.service.ServicoService;

@Service
public class ServicoServiceImpl implements ServicoService {
	
	@Autowired
	private ServicoRepository servico;

	@Override
	public List<Servico> buscarTodos() {
		return servico.findAllServicos();
	}

	@Override
	public Servico buscarPorId(Long id) {
		return servico.findById(id).orElseThrow(() -> new IllegalArgumentException("Serviço " + id + " não encontrado"));
	}

	@Override
	public void excluirPorId(Long id) {
		servico.deleteById(id);
		
	}

	@Override
	public Servico cadastrar(Servico s) {
		
		return servico.save(s);
		
	}

	@Override
	public Servico editar(Servico s) {

		Servico s1 = this.buscarPorId(s.getId());
		
		s1.setTitulo(s.getTitulo());
		s1.setDescricao(s.getDescricao());
		s1.setPrecoMinimo(s.getPrecoMinimo());
		s1.setPrecoMaximo(s.getPrecoMaximo());
		s1.setAtuacao(s.getAtuacao());
		s1.setCategoria(s.getCategoria());
		
		return servico.save(s1);
	}
	
	

}
