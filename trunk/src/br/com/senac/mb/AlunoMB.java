package br.com.senac.mb;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import br.com.senac.dao.AlunoDAO;
import br.com.senac.model.Aluno;

@ManagedBean(name="alunoMB")
@RequestScoped
public class AlunoMB {

	private Aluno aluno;
	private AlunoDAO alunoDAO;
	
	/**
	 * Campo usado para busca
	 */
	private String conteudo;
	
	private List<Aluno> alunos;
	private String destino;
	
	private static Map<String,Object> mapSexo;
	static {
		mapSexo = new LinkedHashMap<String,Object>();
		mapSexo.put("Masculino", "M"); //label, value
		mapSexo.put("Feminio", "F");
	}
	
	public AlunoMB() {
		aluno = new Aluno();
		alunoDAO = new AlunoDAO();
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
	
	public Map<String,Object> getMapSexo() {
		return mapSexo;
	}
	
	public String getDestino() {
		return destino;
	}
	
	public void setDestino(String destino) {
		this.destino = destino;
	}
	
	
	/*
	 * Comandos 
	 */

	public String goUpdate(Aluno alunoSelecionado) {
		aluno = alunoDAO.getById(alunoSelecionado.getId());
		return "aluno/aluno_cadastrar";
	}

	public String inserir() {
		alunoDAO.inserir(aluno);
		return "aluno_listar";
	}
	
	public String alterar() {
		alunoDAO.atualizar(aluno);
		return "aluno_listar";
	}
	
	public void excluir(Aluno alunoSelecionado) {
		alunoDAO.apagar(alunoSelecionado);
	}
	
	public List<Aluno> listar() {
		return getAlunos();
	}
	
	public void buscar() {
		alunos = alunoDAO.buscar(conteudo);
	}
}
