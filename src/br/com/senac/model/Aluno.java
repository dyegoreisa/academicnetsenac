package br.com.senac.model;

import java.util.ArrayList;
import java.util.Date;

public class Aluno extends Pessoa {

	private int matricula;
	private Boolean bolsa;
	private ArrayList<Turma> turmas = new ArrayList<>();

	public Aluno() {}
	
	public Aluno(String nome, String sobrenome, String email,
			int matricula, Boolean bolsa, String vinculo) {
		super(nome, sobrenome, email);
		this.matricula = matricula;
		this.bolsa = bolsa;
		
	}

	public Aluno(int id, String nome, String sobrenome, String sexo,
			String telefone, Date dataNascimento, String email,
			int matricula, Boolean bolsa) {
		super(id, nome, sobrenome, sexo, telefone, dataNascimento, email);
		this.matricula = matricula;
		this.bolsa = bolsa;
	}

	public Aluno(String nome, String sobrenome, String sexo, String telefone,
			Date dataNascimento, String email,int matricula,
			Boolean bolsa, ArrayList<Turma> turmas) {
		super(nome, sobrenome, sexo, telefone, dataNascimento, email);
		this.matricula = matricula;
		this.bolsa = bolsa;
		this.turmas = turmas;
	}

	public Aluno(int id, String nome, String sobrenome, String email,
			int matricula, Boolean bolsa) {
		super(id, nome, sobrenome, email);
		this.matricula = matricula;
		this.bolsa = bolsa;
		
	}

	public Integer getMatricula() {
		return matricula;
	}

	public Boolean getBolsa() {
		return bolsa instanceof Boolean && bolsa == true;
	}

	public void setMatricula(int matricula) {
		this.matricula = matricula;
	}
	
	public void setBolsa(boolean bolsa) {
		this.bolsa = bolsa;
	}
	
	public ArrayList<Turma> getTurmas() {
		return turmas;
	}

	public void addTurma(Turma turma) {
		turmas.add(turma);
	}

	@Override
	public String toString() {
		return "[Nome: " + getNome() + " Matricula: " + matricula + "]";
	}

	
}


