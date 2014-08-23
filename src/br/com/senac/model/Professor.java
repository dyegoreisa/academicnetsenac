package br.com.senac.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@NamedQueries({ 
	@NamedQuery(
			name = "buscarProfessores", 
			query = "SELECT p FROM Professor p WHERE p.nome LIKE :nome OR p.sobrenome LIKE :sobrenome OR p.email LIKE :email "
	) 
})
@XmlRootElement
public class Professor extends Pessoa implements Serializable {

	private static final long serialVersionUID = -8249960545208553388L;

	private String especialidade;
	private String vinculo;
	
	@Transient
	private ArrayList<Leciona> lecionaTurmas = new ArrayList<Leciona>();

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
		List<Turma> turmas = new ArrayList<Turma>();
		for (Leciona lt : lecionaTurmas) {
			turmas.add(lt.getTurma());
		}
		return turmas;
	}
	
	public List<Disciplina> getDisciplinas() {
		List<Disciplina> disciplinas = new ArrayList<Disciplina>();
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
