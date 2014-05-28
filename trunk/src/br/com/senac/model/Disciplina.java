package br.com.senac.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

@Entity
@NamedQueries({ 
	@NamedQuery(
			name = "buscarDisciplinas", 
			query = "SELECT d FROM Disciplina d WHERE d.nome LIKE :nome "
	) 
})
public class Disciplina {
	
	@Id
	@GeneratedValue
	private int id;
	
	private String nome;
	
	public Disciplina() {
		super();
	}
	
	public Disciplina(int id, String nome) {
		super();
		this.id = id;
		this.nome = nome;
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

	public void setMatriculas(Object byDisciplina) {
		// TODO Auto-generated method stub
		
	}
}
