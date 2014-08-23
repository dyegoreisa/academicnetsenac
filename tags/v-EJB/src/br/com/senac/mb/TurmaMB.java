package br.com.senac.mb;

import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import br.com.senac.dao.CursoDAO;
import br.com.senac.dao.TurmaDAO;
import br.com.senac.model.Curso;
import br.com.senac.model.Turma;

@ManagedBean(name="turmaMB")
@RequestScoped
public class TurmaMB {

	private Turma turma;
	
	@EJB
	private TurmaDAO turmaDAO;
	
	@EJB
	private CursoDAO cursoDAO;
	
	private List<Turma> turmas;
	private List<Curso> cursos;
	
	private int acao;
	
	/**
	 * Campo usado para busca
	 */
	private String conteudo;

	public TurmaMB() {
		turma = new Turma();
		acao = 1;
	}

	public Turma getTurma() {
		return turma;
	}

	public void setTurma(Turma turma) {
		this.turma = turma;
	}
	
	public List<Turma> getTurmas() {
		if (turmas == null) {
			turmas = turmaDAO.listar();
		}
		return turmas;
	}

	public void setTurmas(List<Turma> turmas) {
		this.turmas = turmas;
	}

	public String getConteudo() {
		return conteudo;
	}

	public void setConteudo(String conteudo) {
		this.conteudo = conteudo;
	}
		
	public int getAcao() {
		return acao;
	}
	
	public void setAcao(int acao) {
		this.acao = acao;
	}

	public List<Curso> getCursos() {
		if (cursos == null) {
			cursos = cursoDAO.listar();
		}
		return cursos;
	}
	/*
	 * Comandos 
	 */

	public String goUpdate(Turma turmaSelecionado) {
		turma = turmaDAO.getById(turmaSelecionado.getId());
		acao = 2;
		return "cadastrarTurma";
	}

	public String salvar() {
		switch(acao)
		{
			case 1: // Inserir
				turmaDAO.inserir(turma);
				break;
				
			case 2: // Alterar
				turmaDAO.atualizar(turma);
				break;
		}
		
		return "listarTurma";
	}
	
	public void excluir(Turma turmaSelecionado) {
		turmaDAO.apagar(turmaSelecionado);
		turmas = turmaDAO.listar();
	}
	
	public List<Turma> listar() {
		return getTurmas();
	}
	
	public void buscar() {
		turmas = turmaDAO.buscar(conteudo.toString());
	}
	
}