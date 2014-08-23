package br.com.senac.mb;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import br.com.senac.dao.AlunoDAO;
import br.com.senac.model.Aluno;

@ManagedBean(name="alunoMB")
@RequestScoped
public class AlunoMB {

	private Aluno aluno;
	
	@EJB
	private AlunoDAO alunoDAO;
	
	private List<Aluno> alunos;
	private String destino;
	private int acao;
	
	/**
	 * Campo usado para busca
	 */
	private String conteudo;

	private static Map<String,String> mapSexo;
	static {
		mapSexo = new LinkedHashMap<String,String>();
		mapSexo.put("Masculino", "M"); //label, value
		mapSexo.put("Feminino", "F");
	}
	
	public AlunoMB() {
		aluno = new Aluno();
		acao = 1;
	}

	public Aluno getAluno() {
		return aluno;
	}

	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}
	
	public List<Aluno> getAlunos() {
		if (alunos == null) {
			alunos = alunoDAO.listar();
		}
		return alunos;
	}

	public void setAlunos(List<Aluno> alunos) {
		this.alunos = alunos;
	}

	public String getConteudo() {
		return conteudo;
	}

	public void setConteudo(String conteudo) {
		this.conteudo = conteudo;
	}
	
	public Map<String,String> getMapSexo() {
		return mapSexo;
	}
	
	public String getDestino() {
		return destino;
	}
	
	public void setDestino(String destino) {
		this.destino = destino;
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

	public String goUpdate(Aluno alunoSelecionado) {
		aluno = alunoDAO.getById(alunoSelecionado.getId());
		acao = 2;
		return "cadastrarAluno";
	}

	public String salvar() {
		switch(acao)
		{
			case 1: // Inserir
				alunoDAO.inserir(aluno);
				break;
				
			case 2: // Alterar
				alunoDAO.atualizar(aluno);
				break;
		}
		
		return "listarAluno";
	}
	
	public void excluir(Aluno alunoSelecionado) {
		alunoDAO.apagar(alunoSelecionado);
		alunos = alunoDAO.listar();
	}
	
	public List<Aluno> listar() {
		return getAlunos();
	}
	
	public void buscar() {
		alunos = alunoDAO.buscar(conteudo);
	}
}
