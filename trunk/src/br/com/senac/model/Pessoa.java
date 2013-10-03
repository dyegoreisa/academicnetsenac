package br.com.senac.model;

import java.text.SimpleDateFormat;
import java.util.Date;

public abstract class Pessoa {
	private int id;
	private String nome;
	private String sobrenome;
	private String sexo;
	private String telefone;
	private Date dataNascimento;
	private String email;
	
	public Pessoa(String nome, String sobrenome, String email) {
		this.nome = nome;
		this.sobrenome = sobrenome;
		this.email = email;
	}

	public Pessoa(int id, String nome, String sobrenome, String email) {
		this.id = id;
		this.nome = nome;
		this.sobrenome = sobrenome;
		this.email = email;
	}	
	
	public Pessoa(String nome, String sobrenome, String sexo, String telefone,
			Date dataNascimento, String email) {
		this.nome = nome;
		this.sobrenome = sobrenome;
		this.sexo = sexo;
		this.telefone = telefone;
		this.dataNascimento = dataNascimento;
		this.email = email;
	}

	public Pessoa(int id, String nome, String sobrenome, String sexo,
			String telefone, Date dataNascimento, String email) {
		this.id = id;
		this.nome = nome;
		this.sobrenome = sobrenome;
		this.sexo = sexo;
		this.telefone = telefone;
		this.dataNascimento = dataNascimento;
		this.email = email;
	}

	public int getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public String getSobrenome() {
		return sobrenome;
	}

	public String getSexo() {
		return sexo;
	}

	public String getTelefone() {
		return telefone;
	}

	public Date getDataNascimento() {
		return dataNascimento;
	}
	
	public String getDataNascimento(SimpleDateFormat sf) {
		return sf.format(dataNascimento);
	}	
	
	public java.sql.Date getDataNascimentoToSQL() {
		return new java.sql.Date(dataNascimento.getTime());
	}

	public String getEmail() {
		return email;
	}

}
