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
		@UniqueConstraint(columnNames={"id_turma","id_aluno"} ) 
})
public class Matricula implements Serializable{

	private static final long serialVersionUID = 1729908459973324564L;

	@Id
	@ManyToOne
	@JoinColumn(name="id_turma")
	private Turma turma;
	
	@Id
	@ManyToOne
	@JoinColumn(name="id_aluno")
	private Aluno aluno;
	
	private String codigo;
	private boolean ativa;
	
	public Matricula () {}

	public Matricula(String codigo, boolean ativa) {
		super();
		this.codigo = codigo;
		this.ativa = ativa;
	}
	
	public Matricula(Turma turma, Aluno aluno, String codigo, boolean ativa) {
		super();
		this.turma = turma;
		this.aluno = aluno;
		this.codigo = codigo;
		this.ativa = ativa;
	}
	
	public Matricula(Turma turma, Aluno aluno) {
		this.turma = turma;
		this.aluno = aluno;
	}

	public Aluno getAluno() {
		return aluno;
	}
	
	public Turma getTurma() {
		return turma;
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
