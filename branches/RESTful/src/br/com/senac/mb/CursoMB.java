package br.com.senac.mb;

import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import br.com.senac.dao.CursoDAO;
import br.com.senac.model.Curso;

@ManagedBean(name="cursoMB")
@RequestScoped
public class CursoMB {

	private Curso curso;
	
	@EJB
	private CursoDAO cursoDAO;
	
	private List<Curso> cursos;
	private int acao;
	
	/**
	 * Campo usado para busca
	 */
	private String conteudo;
	
	public CursoMB() {
		curso = new Curso();
		acao = 1;
	}

	public Curso getCurso() {
		return curso;
	}

	public void setCurso(Curso curso) {
		this.curso = curso;
	}
	
	public List<Curso> getCursos() {
		if (cursos == null) {
			cursos = cursoDAO.listar();
		}
		return cursos;
	}

	public void setCursos(List<Curso> cursos) {
		this.cursos = cursos;
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
	
	/*
	 * Comandos 
	 */

	public String goUpdate(Curso cursoSelecionado) {
		curso = cursoDAO.getById(cursoSelecionado.getId());
		acao = 2;
		return "cadastrarCurso";
	}

	public String salvar() {
		switch(acao)
		{
			case 1: // Inserir
				cursoDAO.inserir(curso);
				break;
				
			case 2: // Alterar
				cursoDAO.atualizar(curso);
				break;
		}
		
		return "listarCurso";
	}
	
	public void excluir(Curso cursoSelecionado) {
		cursoDAO.apagar(cursoSelecionado);
		cursos = cursoDAO.listar();
	}
	
	public List<Curso> listar() {
		return getCursos();
	}
	
	public void buscar() {
		cursos = cursoDAO.buscar(conteudo);
	}
}
