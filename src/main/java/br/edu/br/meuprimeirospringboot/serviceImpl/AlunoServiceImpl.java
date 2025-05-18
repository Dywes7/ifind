package br.edu.br.meuprimeirospringboot.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.edu.br.meuprimeirospringboot.entity.Aluno;
import br.edu.br.meuprimeirospringboot.repository.AlunoRepository;
import br.edu.br.meuprimeirospringboot.service.AlunoService;


@Service
public class AlunoServiceImpl  implements AlunoService{
	
	@Autowired
	private AlunoRepository aluno;
	
	@Override
	public List<Aluno> buscarTodos() {
		return aluno.findAllAlunos();
	}

	@Override
	public Aluno buscarPorId(Long id) {
		return aluno.findById(id).orElseThrow(() -> new IllegalArgumentException("Aluno " + id + " não encontrado"));
	}

	@Override
	public void excluirPorId(Long id) {
		aluno.deleteById(id);
	}
	
	public boolean isCpfValido(String cpf) {
		return cpf != null && cpf.length() == 14;
	}

	@Override
	@Transactional
	public Aluno cadastrar(Aluno a) {
		
		if (!isCpfValido(a.getCpf())) {
			throw new RuntimeException("CPF deve conter 11 caracteres.");
		}
		
		if (aluno.existsByCpf(a.getCpf())) {
	        throw new RuntimeException("CPF já cadastrado!");
	    }
		 
		return aluno.save(a);
	}

	@Override
	public Aluno editar(Aluno a) {
		Aluno a1 = this.buscarPorId(a.getId());
		
		if (!isCpfValido(a.getCpf())) {
			throw new RuntimeException("CPF deve conter 11 caracteres.");
		}
		
		if (!a.getCpf().equals(a1.getCpf()) && aluno.existsByCpf(a.getCpf())) {
			throw new RuntimeException("CPF já cadastrado!");
		}
		
		a1.setNome(a.getNome());
		a1.setEmail(a.getEmail());
		a1.setMatricula(a.getMatricula());
		a1.setCpf(a.getCpf());
		a1.setDtNascimento(a.getDtNascimento());
		return aluno.save(a1);
	}

}
