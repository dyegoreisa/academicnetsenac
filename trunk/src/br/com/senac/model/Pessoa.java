package br.com.senac.model;

import java.text.SimpleDateFormat;
import java.util.Date;

public abstract class Pessoa {
	private int id;
	private String nome;
	private String sobrenome;
	private Date dataNascimento;
	
	public Pessoa(String nome, String sobrenome, Date dataNascimento) {
		this.nome = nome;
		this.sobrenome = sobrenome;
		this.dataNascimento = dataNascimento;
	}

	public Pessoa(int id, String nome, String sobrenome, Date dataNascimento) {
		super();
		this.id = id;
		this.nome = nome;
		this.sobrenome = sobrenome;
		this.dataNascimento = dataNascimento;
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

	public Date getDataNascimento() {
		return dataNascimento;
	}

	public String getDataNascimento(SimpleDateFormat sf) {
		return sf.format(dataNascimento);
	}
	
}
