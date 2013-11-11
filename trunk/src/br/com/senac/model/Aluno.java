package br.com.senac.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@DiscriminatorValue("Aluno")
@Table(name="Aluno")
public class Aluno extends Pessoa {

	private Boolean bolsa;
	
	@OneToMany(mappedBy = "aluno", targetEntity = Matricula.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Matricula> matriculas;

	public Aluno() {
		matriculas = new ArrayList<>();
	}
	
	public Aluno(String nome, String sobrenome, String email,
			List<Matricula> matriculas, Boolean bolsa, String vinculo) {
		super(nome, sobrenome, email);
		this.matriculas = matriculas;
		this.bolsa = bolsa;
		
	}

	public Aluno(int id, String nome, String sobrenome, String sexo,
			List<Telefone> telefones, Date dataNascimento, String email,
			List<Matricula> matriculas, Boolean bolsa) {
		super(id, nome, sobrenome, sexo, telefones, dataNascimento, email);
		this.matriculas = matriculas;
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
	}
	
}
