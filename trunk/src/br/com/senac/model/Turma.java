package br.com.senac.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
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
	
	@OneToMany(mappedBy = "turma", targetEntity = Matricula.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Matricula> matriculas;
	
	public Turma() {}

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

	public void setId(int id) {
		this.id = id;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setDataInicio(Date dataInicio) {
		this.dataInicio = dataInicio;
	}
	
	public void setDataInicio(String dataInicio) {
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		try {
			this.dataInicio = formatter.parse(dataInicio);
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}

	public void setDataFim(Date dataFim) {
		this.dataFim = dataFim;
	}
	
	public void setDataFim(String dataFim) {
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		try {
			this.dataFim = formatter.parse(dataFim);
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}

	public void setPrevisaoTermino(Date previsaoTermino) {
		this.previsaoTermino = previsaoTermino;
	}
	
	public void setPrevisaoTermino(String previsaoTermino) {
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		try {
			this.previsaoTermino = formatter.parse(previsaoTermino);
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void setCurso(Curso curso) {
		this.curso = curso;
	}
	
}
