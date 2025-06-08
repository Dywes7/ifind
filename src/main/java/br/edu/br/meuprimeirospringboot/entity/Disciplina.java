package br.edu.br.meuprimeirospringboot.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Disciplina {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@Column(name = "nome", unique = true, nullable = false)
	private String nome;
	private int cargaHorariaHoras;
	@Column(columnDefinition = "TEXT")
	private String ementa;
	
	
	
	
	
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "disciplina", cascade = CascadeType.ALL)
	private List<Turma> turmas = new ArrayList<Turma>();
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public int getCargaHorariaHoras() {
		return cargaHorariaHoras;
	}
	public void setCargaHorariaHoras(int cargaHorariaHoras) {
		this.cargaHorariaHoras = cargaHorariaHoras;
	}
	public String getEmenta() {
		return ementa;
	}
	public void setEmenta(String ementa) {
		this.ementa = ementa;
	}

	public List<Turma> getTurmas() {
		return turmas;
	}
	public void setTurmas(List<Turma> turmas) {
		this.turmas = turmas;
	}
	

}
