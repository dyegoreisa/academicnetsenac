package br.com.senac.model;

public class Telefone {
	private String numero;
	private String contato;
	
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
