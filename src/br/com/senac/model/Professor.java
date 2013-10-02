package br.com.senac.model;

import java.util.Date;

public class Professor extends Pessoa{

	public Professor(int id, String nome, String sobrenome, Date dataNascimento) {
		super(id, nome, sobrenome, dataNascimento);
	}

	public Professor(String nome, String sobrenome, Date dataNascimento) {
		super(nome, sobrenome, dataNascimento);
	}	
}
