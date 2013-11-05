package br.com.senac.model;

import java.util.ArrayList;
import java.util.Date;

public class Aluno extends Pessoa {

	private Boolean bolsa;
	private ArrayList<Matricula> matriculas = new ArrayList<>();
	private Matricula matriculaAtiva;

	public Aluno() {}
	
	public Aluno(String nome, String sobrenome, String email,
			ArrayList<Matricula> matriculas, Boolean bolsa, String vinculo) {
		super(nome, sobrenome, email);
		this.matriculas = matriculas;
		this.bolsa = bolsa;
		
	}

	public Aluno(int id, String nome, String sobrenome, String sexo,
			ArrayList<Telefone> telefones, Date dataNascimento, String email,
			ArrayList<Matricula> matriculas, Boolean bolsa) {
		super(id, nome, sobrenome, sexo, telefones, dataNascimento, email);
		this.matriculas = matriculas;
		this.bolsa = bolsa;
	}

	public Matricula getMatriculaAtiva() {
		if (matriculaAtiva == null) {
			for (Matricula m : matriculas) {
				if (m.isAtiva()) {
					matriculaAtiva = m;
					break;
				}
			}
		}
		return matriculaAtiva;
	}

	public Boolean getBolsa() {
		return bolsa instanceof Boolean && bolsa == true;
	}
	
	public void setBolsa(boolean bolsa) {
		this.bolsa = bolsa;
	}
	
	@Override
	public String toString() {
		return "[Nome: " + getNome() + " Matricula: " + getMatriculaAtiva() + "]";
	}
	
}


