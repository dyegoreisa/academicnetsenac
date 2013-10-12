package br.com.senac.model;

import java.util.ArrayList;

public class Turma {
	private int id;
	private String nome;
	private ArrayList<Professor> professores;
	
	public Turma() {}
	
	public Turma(int id, String nome, ArrayList<Professor> professores) {
		this.id = id;
		this.nome = nome;
		this.professores = professores;
	}

	public Turma(int id, String nome) {
		this.id = id;
		this.nome = nome;
	}
	
	public Turma(String nome, ArrayList<Professor> professores) {
		this.nome = nome;
		this.professores = professores;
	}

	public int getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}

	public ArrayList<Professor> getProfessores() {
		return professores;
	}

}
