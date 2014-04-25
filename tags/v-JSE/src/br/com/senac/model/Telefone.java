package br.com.senac.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(uniqueConstraints = {
		@UniqueConstraint(columnNames={"numero","id_pessoa"})
})
public class Telefone implements Serializable{

	private static final long serialVersionUID = 6838260689978965727L;

	@Id
	private String numero;
	private String contato;
	
	@Id
	@ManyToOne
	@JoinColumn(name="id_pessoa")
	private Pessoa pessoa;
	
	public Telefone() {}
	
	public Telefone(String numero, String contato) {
		super();
		this.numero = numero;
		this.contato = contato;
	}

	public String getNumero() {
		return numero;
	}

	public String getContato() {
		return contato;
	}
		
}
