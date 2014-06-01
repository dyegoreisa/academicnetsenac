package br.com.senac.mb;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.inject.Inject;
import javax.jms.JMSConnectionFactory;
import javax.jms.JMSContext;
import javax.jms.JMSException;
import javax.jms.ObjectMessage;
import javax.jms.Queue;

import br.com.senac.dao.AlunoDAO;
import br.com.senac.dao.MatriculaDAO;
import br.com.senac.dao.TurmaDAO;
import br.com.senac.model.Aluno;
import br.com.senac.model.Matricula;
import br.com.senac.model.Turma;

@ManagedBean(name="matriculaMB")
@RequestScoped
public class MatriculaMB {

	@Resource(mappedName = "java:/queue/matriculaQueue")
	private Queue fila;
 
	@Inject
	@JMSConnectionFactory("java:/ConnectionFactory")
	private JMSContext context;	
	
	@EJB
	private MatriculaDAO matriculaDAO;
	
	@EJB
	private TurmaDAO turmaDAO;
	
	@EJB
	private AlunoDAO alunoDAO;

	private Matricula matricula;
	private Turma turma;
	
	private String idTurma;
	private String[] alunosSelecionados;
	
	private List<Turma> turmas;
	private List<Aluno> alunos;
	
	private List<Matricula> matriculas;
	
	private String destino;
	private int acao;
	
	/**
	 * Campo usado para busca
	 */
	private String conteudo;
	
	public MatriculaMB() {
		matricula = new Matricula();
		acao = 1;
	}
		
	public Matricula getMatricula() {
		return matricula;
	}

	public Turma getTurma() {
		return turma;
	}

	public String getIdTurma() {
		return idTurma;
	}
	
	public String[] getAlunosSelecionados() {
		return alunosSelecionados;
	}

	public List<Turma> getTurmas() {
		turmas = turmaDAO.listar();
		return turmas;
	}

	public List<Aluno> getAlunos() {
		alunos = alunoDAO.listar();
		return alunos;
	}
	
	public String getDestino() {
		return destino;
	}

	public int getAcao() {
		return acao;
	}

	public String getConteudo() {
		return conteudo;
	}

	public void setMatricula(Matricula matricula) {
		this.matricula = matricula;
	}

	public void setTurma(Turma turma) {
		this.turma = turma;
	}

	public void setIdTurma(String idTurma) {
		this.idTurma = idTurma;
	}
	
	public void setAlunosSelecionados(String[] alunosSelecionados) {
		this.alunosSelecionados = alunosSelecionados;
	}

	public void setTurmas(List<Turma> turmas) {
		this.turmas = turmas;
	}

	public void setAlunos(List<Aluno> alunos) {
		this.alunos = alunos;
	}
	
	public void setDestino(String destino) {
		this.destino = destino;
	}

	public void setAcao(int acao) {
		this.acao = acao;
	}

	public void setConteudo(String conteudo) {
		this.conteudo = conteudo;
	}

	public List<Matricula> getMatriculas() {
		return matriculas;
	}
	
	public void setMatriculas(List<Matricula> matriculas) {
		this.matriculas = matriculas;
	}
	
	/*
	 * Comandos 
	 */
	public String salvar() {
		
		turma = turmaDAO.getById(Integer.parseInt(idTurma));
		
		for(int i = 0; i < alunosSelecionados.length; i++){
			Aluno alunoAtual = alunoDAO.getById(Integer.parseInt(alunosSelecionados[i]));
			
			StringBuffer sbCodigo = new StringBuffer();
            
            DateFormat dateFormatYear = new SimpleDateFormat("yyyy");
            Date date = new Date();
            
            sbCodigo.append(dateFormatYear.format(date));
            sbCodigo.append(alunoAtual.getNome().substring(0, 2).toUpperCase());
            sbCodigo.append(alunoAtual.getSobrenome().substring(0, 2).toUpperCase());
            sbCodigo.append(1000 + (int)(Math.random() * ((9999 - 1000) + 1)));
         
            String codigo = sbCodigo.toString();
            
            Matricula matricula = new Matricula(turma, alunoAtual, codigo, true);
            matriculaDAO.inserir(matricula);
            try {
    			ObjectMessage objMessage = context.createObjectMessage();
    			objMessage.setObject(matricula);
    			context.createProducer().send(fila, objMessage);
     
    		} catch (JMSException ex) {
    			ex.printStackTrace();
    		}
		}
		return "listarTurma";
	}

	public String listarAlunosTurma(Turma turma) {
		matriculas = matriculaDAO.getByTurma(turma);
		return "alunoTurma";
	}
	
}