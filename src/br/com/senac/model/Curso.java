package br.com.senac.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Curso {
	
	@Id @GeneratedValue
	private int id;
	
	private String nome;
	
	@OneToMany(mappedBy = "curso", targetEntity = Turma.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Turma> turmas;
	
	public Curso() {
		super();
		turmas = new ArrayList<Turma>();
	}
	
	public Curso(int id, String nome) {
		super();
		this.id = id;
		this.nome = nome;
		turmas = new ArrayList<Turma>();
	}
	
	public int getId() {
		return id;
	}
	
	public String getNome() {
		return nome;
	}
	
	public List<Turma> getTurmas() {
		return turmas;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public void setTurmas(List<Turma> turmas) {
		this.turmas = turmas;
	}

	@Override
	public String toString() {
		return id + " - " + nome;
	}
	
}
