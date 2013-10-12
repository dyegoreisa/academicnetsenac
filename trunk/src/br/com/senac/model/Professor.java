package br.com.senac.model;

import java.util.ArrayList;
import java.util.Date;

public class Professor extends Pessoa {

	private String especialidade;
	private double salario;
	private String vinculo;
	private ArrayList<Turma> turmas = new ArrayList<>();

	public Professor(String nome, String sobrenome, String email,
			String especialidade, double salario, String vinculo) {
		super(nome, sobrenome, email);
		this.especialidade = especialidade;
		this.salario = salario;
		this.vinculo = vinculo;
	}

	public Professor(int id, String nome, String sobrenome, String sexo,
			String telefone, Date dataNascimento, String email,
			String especialidade, double salario, String vinculo) {
		super(id, nome, sobrenome, sexo, telefone, dataNascimento, email);
		this.especialidade = especialidade;
		this.salario = salario;
		this.vinculo = vinculo;
	}

	public Professor(String nome, String sobrenome, String sexo, String telefone,
			Date dataNascimento, String email, String especialidade,
			double salario, String vinculo, ArrayList<Turma> turmas) {
		super(nome, sobrenome, sexo, telefone, dataNascimento, email);
		this.especialidade = especialidade;
		this.salario = salario;
		this.vinculo = vinculo;
		this.turmas = turmas;
	}

	public Professor(int id, String nome, String sobrenome, String email,
			String especialidade, double salario, String vinculo) {
		super(id, nome, sobrenome, email);
		this.especialidade = especialidade;
		this.salario = salario;
		this.vinculo = vinculo;
	}

	public Professor() {
		super();
	}

	public String getEspecialidade() {
		return especialidade;
	}

	public double getSalario() {
		return salario;
	}

	public String getVinculo() {
		return vinculo;
	}

	public ArrayList<Turma> getTurmas() {
		return turmas;
	}

	public void setEspecialidade(String especialidade) {
		this.especialidade = especialidade;
	}

	public void setSalario(double salario) {
		this.salario = salario;
	}

	public void setVinculo(String vinculo) {
		this.vinculo = vinculo;
	}

	public void addTurma(Turma turma) {
		turmas.add(turma);
	}

	@Override
	public String toString() {
		return "[Nome: " + getNome() + " Especialidade: " + especialidade + "]";
	}

}
