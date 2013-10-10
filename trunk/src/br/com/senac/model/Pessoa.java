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
	
	public Pessoa() {}

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
		if (dataNascimento != null) {
			return sf.format(dataNascimento);
		}
		return "";
	}	
	
	public java.sql.Date getDataNascimentoToSQL() {
		return new java.sql.Date(dataNascimento.getTime());
	}

	public String getEmail() {
		return email;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setSobrenome(String sobrenome) {
		this.sobrenome = sobrenome;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
