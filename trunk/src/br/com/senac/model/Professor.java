package br.com.senac.model;

import java.util.List;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@DiscriminatorValue("Professor")
@Table(name="Professor")
public class Professor extends Pessoa {

	private String especialidade;
	private String vinculo;
	
	@ManyToMany
	@JoinTable(name = "leciona",
			joinColumns = @JoinColumn(name = "id_professor"),
			inverseJoinColumns = @JoinColumn(name = "id_turma"))
	private List<Turma> turmas;

	public Professor() {}
	
	public Professor(int id, String nome) {
		super(id, nome, "", "");
	}
	
	public Professor(String nome, String sobrenome, String email,
			String especialidade, String vinculo) {
		super(nome, sobrenome, email);
		this.especialidade = especialidade;
		this.vinculo = vinculo;
	}
	
	public String getEspecialidade() {
		if (especialidade instanceof String) {
			return especialidade;
		} else {
			return "";
		}
	}

	public String getVinculo() {
		if (vinculo instanceof String) {
			return vinculo;
		} else {
			return "";
		}
	}
	
	public void setEspecialidade(String especialidade) {
		this.especialidade = especialidade;
	}
	
	public void setVinculo(String vinculo) {
		this.vinculo = vinculo;
	}
	
	public List<Turma> getTurmas() {
		return turmas;
	}
	
	@Override
	public String toString() {
		return getId() + " - " + getNome() + " [" + getEspecialidade() + "]";
	}

}
