package br.com.senac.model;

import java.util.ArrayList;

public class Professor extends Pessoa {

	private String especialidade;
	private String vinculo;
	private ArrayList<Leciona> lecionaTurmas = new ArrayList<>();

	public Professor() {}
	
	public Professor(String nome, String sobrenome, String email,
			String especialidade, String vinculo) {
		super(nome, sobrenome, email);
		this.especialidade = especialidade;
		this.vinculo = vinculo;
	}
	
	public String getEspecialidade() {
		return especialidade;
	}

	public String getVinculo() {
		return vinculo;
	}
	
	public ArrayList<Turma> getTurmas() {
		ArrayList<Turma> turmas = new ArrayList<>();
		for (Leciona lt : lecionaTurmas) {
			turmas.add(lt.getTurma());
		}
		return turmas;
	}
	
	public ArrayList<Disciplina> getDisciplinas() {
		ArrayList<Disciplina> disciplinas = new ArrayList<>();
		for (Leciona lt : lecionaTurmas) {
			disciplinas.add(lt.getDisciplina());
		}
		return disciplinas;
	}
	
	public void addLecionaTurma(Turma turma, Disciplina disciplina) {
		lecionaTurmas.add(new Leciona(turma, disciplina));
	}

	@Override
	public String toString() {
		return "[Nome: " + getNome() + " Especialidade: " + especialidade + "]";
	}
	
	private final class Leciona {
		private Turma turma;
		private Disciplina disciplina;
		
		public Leciona(Turma turma, Disciplina disciplina) {
			super();
			this.turma = turma;
			this.disciplina = disciplina;
		}

		public Turma getTurma() {
			return turma;
		}

		public Disciplina getDisciplina() {
			return disciplina;
		}

	}

}
