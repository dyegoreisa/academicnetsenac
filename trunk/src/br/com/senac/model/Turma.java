package br.com.senac.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Turma {
	
	@Id @GeneratedValue
	private int id;
	
	private String nome;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "data_inicio")
	private Date dataInicio;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "data_fim")
	private Date dataFim;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "previsao_termino")
	private Date previsaoTermino;
	
	@ManyToOne
	@JoinColumn(name="id_curso")
	private Curso curso;
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "matricula",
			joinColumns = @JoinColumn(name = "id_turma"),
			inverseJoinColumns = @JoinColumn(name = "id_aluno"))
	private List<Aluno> alunos;

	@ManyToMany
	@JoinTable(name = "leciona",
			joinColumns = @JoinColumn(name = "id_turma"),
			inverseJoinColumns = @JoinColumn(name = "id_professor"))
	private List<Professor> professores;
	
	public Turma() {
	}

	public Turma(int id, String nome) {
		this.id = id;
		this.nome = nome;
	}

	public int getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public Date getDataInicio() {
		return dataInicio;
	}

	public Date getDataFim() {
		return dataFim;
	}

	public Date getPrevisaoTermino() {
		return previsaoTermino;
	}

	public Curso getCurso() {
		return curso;
	}

	public void addAluno(Aluno aluno) {
		this.alunos.add(aluno);
	}
	
	public void addProfessor(Professor professor) {
		this.professores.add(professor);
	}
	
	public void setId(int id) {
		this.id = id;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setDataInicio(Date dataInicio) {
		this.dataInicio = dataInicio;
	}

	public void setDataFim(Date dataFim) {
		this.dataFim = dataFim;
	}

	public void setPrevisaoTermino(Date previsaoTermino) {
		this.previsaoTermino = previsaoTermino;
	}

	public void setCurso(Curso curso) {
		this.curso = curso;
	}
	
}
