package br.com.senac.model;

public class Matricula {
	private String codigo;
	private boolean ativa;

	public Matricula(String codigo, boolean ativa) {
		super();
		this.codigo = codigo;
		this.ativa = ativa;
	}

	public String getCodigo() {
		return codigo;
	}
	
	public boolean isAtiva() {
		return ativa;
	}

	@Override
	public String toString() {
		return "[codigo=" + codigo + "]";
	}

}
