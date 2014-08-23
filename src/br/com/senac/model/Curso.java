package br.com.senac.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@NamedQueries({ 
	@NamedQuery(
			name = "buscarCursos", 
			query = "SELECT c FROM Curso c WHERE c.nome LIKE :nome "
	) 
})
@XmlRootElement
public class Curso implements Serializable {

	private static final long serialVersionUID = -1393004898310976195L;

	@Id 
	@GeneratedValue(strategy=GenerationType.SEQUENCE)
	private int id;
	
	private String nome;
	
	public Curso() {
		super();
	}
	
	public Curso(int id, String nome) {
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
	
	@Override
	public String toString() {
		return id + " - " + nome;
	}
	
}
