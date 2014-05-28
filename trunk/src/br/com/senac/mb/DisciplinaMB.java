package br.com.senac.mb;

import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import br.com.senac.dao.DisciplinaDAO;
import br.com.senac.model.Disciplina;

@ManagedBean(name="disciplinaMB")
@RequestScoped
public class DisciplinaMB {

	private Disciplina disciplina;
	
	@EJB
	private DisciplinaDAO disciplinaDAO;
	
	private List<Disciplina> disciplinas;
	private int acao;
	
	/**
	 * Campo usado para busca
	 */
	private String conteudo;
	
	public DisciplinaMB() {
		disciplina = new Disciplina();
		acao = 1;
	}

	public Disciplina getDisciplina() {
		return disciplina;
	}

	public void setDisciplina(Disciplina disciplina) {
		this.disciplina = disciplina;
	}
	
	public List<Disciplina> getDisciplinas() {
		if (disciplinas == null) {
			disciplinas = disciplinaDAO.listar();
		}
		return disciplinas;
	}

	public void setDisciplinas(List<Disciplina> disciplinas) {
		this.disciplinas = disciplinas;
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

	public String goUpdate(Disciplina disciplinaSelecionada) {
		disciplina = disciplinaDAO.getById(disciplinaSelecionada.getId());
		acao = 2;
		return "cadastrarDisciplina";
	}

	public String salvar() {
		switch(acao)
		{
			case 1: // Inserir
				disciplinaDAO.inserir(disciplina);
				break;
				
			case 2: // Alterar
				disciplinaDAO.atualizar(disciplina);
				break;
		}
		
		return "listarDisciplina";
	}
	
	public void excluir(Disciplina disciplinaSelecionada) {
		disciplinaDAO.apagar(disciplinaSelecionada);
		disciplinas = disciplinaDAO.listar();
	}
	
	public List<Disciplina> listar() {
		return getDisciplinas();
	}
	
	public void buscar() {
		disciplinas = disciplinaDAO.buscar(conteudo);
	}
}
