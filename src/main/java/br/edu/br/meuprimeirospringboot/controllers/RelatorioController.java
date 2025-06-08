package br.edu.br.meuprimeirospringboot.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import br.edu.br.meuprimeirospringboot.serviceImpl.TurmaServiceImpl;

@Controller
@RequestMapping("/relatorio")
public class RelatorioController {
	
	@Autowired
	private TurmaServiceImpl turma;
	// private AlunoServiceImpl aluno;
	
	@GetMapping("")
	String ListarRelatorio(ModelMap model) {
		/* o método está adicionando um atributo chamado "disciplinas" ao objeto model.
		   ModelMap é usado para passar dados do controlador para a view do Thymeleaf. */
		// model.addAttribute("disciplinas", disciplina.buscarTodos());
		model.addAttribute("turmas", turma.buscarTodos());
		return "relatorio";
	}

}
