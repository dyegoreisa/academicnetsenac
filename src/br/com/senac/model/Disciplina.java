package br.com.senac.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Disciplina {
	
	@Id @GeneratedValue
	private int id;
	
	private String nome;
	
	@ManyToOne
	@JoinColumn(name="id_professor")
	private Professor professor;
	
	public Disciplina() {
		super();
	}
	
	public Disciplina(int id, String nome, Professor professor) {
		super();
		this.id = id;
		this.nome = nome;
		this.professor = professor;
	}
	
	public int getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}
	
	public Professor getProfessor() {
		return professor;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public void setProfessor(Professor professor) {
		this.professor = professor;
	}
}
