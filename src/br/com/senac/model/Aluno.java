package br.com.senac.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

@Entity
@NamedQueries({ @NamedQuery(name = "buscarAlunos", query = "SELECT a FROM Aluno a WHERE a.nome LIKE :nome OR a.sobrenome LIKE :sobrenome OR a.email LIKE :email ") })
public class Aluno extends Pessoa implements Serializable {

	private static final long serialVersionUID = 5488478707385938620L;

	private Boolean bolsa;

	@OneToMany(mappedBy = "aluno")
	private List<Matricula> matriculas;

	@Transient
	private Matricula matriculaAtiva;

	public Aluno() {
		matriculas = new ArrayList<Matricula>();
	}

	public Aluno(String nome, String sobrenome, String email,
			List<Matricula> matriculas, Boolean bolsa, String vinculo) {
		super(nome, sobrenome, email);
		this.matriculas = matriculas;
		this.bolsa = bolsa;

	}

	public Aluno(int id, String nome, String sobrenome, String sexo,
			String telefone, Date dataNascimento, String email,
			List<Matricula> matriculas, Boolean bolsa) {
		super(id, nome, sobrenome, sexo, telefone, dataNascimento, email);
		this.matriculas = matriculas;
		this.bolsa = bolsa;
	}

	public Matricula getMatriculaAtiva() {
		if (matriculaAtiva == null) {
			for (Matricula m : matriculas) {
				if (m.isAtiva()) {
					matriculaAtiva = m;
					break;
				}
			}
		}
		return matriculaAtiva;
	}

	public Boolean getBolsa() {
		return bolsa instanceof Boolean && bolsa == true;
	}

	public void setBolsa(boolean bolsa) {
		this.bolsa = bolsa;
	}

	@Override
	public String toString() {
		return "[Nome: " + getNome() + " Matricula: " + getMatriculaAtiva()
				+ "]";
	}

	public void setMatriculas(List<Matricula> matriculas) {
		this.matriculas = matriculas;
	}

	public List<Matricula> getMatriculas() {
		return matriculas;
	}

}
