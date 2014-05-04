package br.com.senac.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@DiscriminatorValue("Professor")
@Table(name="Professor")
public class Professor extends Pessoa {

	private String especialidade;
	private String vinculo;
	
	@Transient
	private ArrayList<Leciona> lecionaTurmas = new ArrayList();

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
	
	public void setEspecialidade(String especialidade) {
		this.especialidade = especialidade;
	}
	
	public void setVinculo(String vinculo) {
		this.vinculo = vinculo;
	}
	
	public List<Turma> getTurmas() {
		List<Turma> turmas = new ArrayList();
		for (Leciona lt : lecionaTurmas) {
			turmas.add(lt.getTurma());
		}
		return turmas;
	}
	
	public List<Disciplina> getDisciplinas() {
		List<Disciplina> disciplinas = new ArrayList();
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
