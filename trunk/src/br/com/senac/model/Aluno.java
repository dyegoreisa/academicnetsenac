package br.com.senac.model;

import java.util.Date;
import java.util.List;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@DiscriminatorValue("Aluno")
@Table(name="Aluno")
public class Aluno extends Pessoa {

	private Boolean bolsa;
	
	@ManyToMany
	@JoinTable(name="matricula",
			joinColumns=@JoinColumn(name="id_aluno"),
			inverseJoinColumns=@JoinColumn(name="id_turma"))
	private List<Turma> turmas;
	
	
	public Aluno() {
	}
	
	public Aluno(String nome, String sobrenome, String email,
			List<Turma> turmas, Boolean bolsa, String vinculo) {
		super(nome, sobrenome, email);
		this.turmas = turmas;
		this.bolsa = bolsa;
		
	}

	public Aluno(int id, String nome, String sobrenome, String sexo,
			List<Telefone> telefones, Date dataNascimento, String email,
			List<Turma> turmas, Boolean bolsa) {
		super(id, nome, sobrenome, sexo, telefones, dataNascimento, email);
		this.turmas = turmas;
		this.bolsa = bolsa;
	}

	public Boolean getBolsa() {
		return bolsa instanceof Boolean && bolsa == true;
	}
	
	public void setBolsa(boolean bolsa) {
		this.bolsa = bolsa;
	}
	
	@Override
	public String toString() {
		return "[Nome: " + getNome() + "]";
		//return "[Nome: " + getNome() + ", " + getMatriculaAtiva() + "]";
	}
	
}
