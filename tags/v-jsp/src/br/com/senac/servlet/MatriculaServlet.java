package br.com.senac.servlet;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.senac.dao.AlunoDAO;
import br.com.senac.dao.MatriculaDAO;
import br.com.senac.dao.TurmaDAO;
import br.com.senac.model.Aluno;
import br.com.senac.model.Matricula;
import br.com.senac.model.Turma;

/**
 * Servlet implementation class MatriculaServlet
 */
@WebServlet("/MatriculaServlet")
public class MatriculaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MatriculaServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		MatriculaDAO matriculaDAO = new MatriculaDAO();
		List<Matricula> matriculas = new ArrayList();
		
		Aluno aluno = new Aluno();
		AlunoDAO alunoDAO = new AlunoDAO();
		
		Turma turma = new Turma();
		TurmaDAO turmaDAO = new TurmaDAO();
		
		Turma turmaSelecionada = null;
		Aluno alunoSelecionado = null;
						
		String mensagem = "";
		String destino = "matricula/matricular.jsp";
		
		int acao = 0;
		
		try {

			if (request.getParameter("acao") != null) {
				acao = Integer.parseInt(request.getParameter("acao"));
			}

			switch(acao)
			{
			case 1: // Inserir

				turmaSelecionada = turmaDAO.getById(Integer.parseInt(request.getParameter("turma")));
				
				String[] idsAlunosStr = request.getParameterValues("alunos");
				Integer[] idsAlunos = new Integer[idsAlunosStr.length];
				
				for(int i = 0; i < idsAlunosStr.length; i++){
					idsAlunos[i] = Integer.parseInt(idsAlunosStr[i]);
				}
				
				List<Aluno> alunosSelecionados = alunoDAO.getListByIds(idsAlunos);
				
				for(Aluno item : alunosSelecionados) {
					StringBuffer sbCodigo = new StringBuffer();
					
					DateFormat dateFormatYear = new SimpleDateFormat("yyyy");
					Date date = new Date();
					
					sbCodigo.append(dateFormatYear.format(date));
					sbCodigo.append(item.getNome().substring(0, 2).toUpperCase());
					sbCodigo.append(item.getSobrenome().substring(0, 2).toUpperCase());
					sbCodigo.append(1000 + (int)(Math.random() * ((9999 - 1000) + 1)));
					
					
					String codigo = sbCodigo.toString();
					
					matriculaDAO.inserir(new Matricula(turmaSelecionada, item, codigo, true));
				}
				
				mensagem = "Aluno matriculado";
				destino = "sucesso.jsp";
				request.setAttribute("listaMatriculas", matriculas);
				break;
				
			case 2: // Atualizar
				break;

			case 3: // Excluir
				turmaSelecionada = turmaDAO.getById(Integer.parseInt(request.getParameter("id_turma")));
				alunoSelecionado = alunoDAO.getById(Integer.parseInt(request.getParameter("id_aluno")));
				
				matriculaDAO.apagar(new Matricula(turmaSelecionada, alunoSelecionado));
				
				mensagem = "Matricula cancelada!";
							
				if (request.getParameter("destino").toString().equals("aluno")) {
					alunoSelecionado.setMatriculas(matriculaDAO.getByAluno(alunoSelecionado));
					request.setAttribute("aluno", alunoSelecionado);
					destino = "/matricula/turmas_aluno.jsp";
				} else if (request.getParameter("destino").toString().equals("turma")) {
					turmaSelecionada.setMatriculas(matriculaDAO.getByTurma(turmaSelecionada));
					request.setAttribute("turma", turmaSelecionada);
					destino = "/matricula/alunos_turma.jsp";
				}
				
				break;
				
			case 4: // Tela matriculas aluno na turma
				List<Aluno> alunos = alunoDAO.listar();
				List<Turma> turmas = turmaDAO.listar();

				request.setAttribute("listaTurmas", turmas);				
				request.setAttribute("listaAlunos", alunos);
				
				destino = "/matricula/matricular_aluno_turma.jsp";
				break;
				
			case 5: // 
				break;
				
			case 6: // Alunos da turma
				turmaSelecionada = turmaDAO.getById(Integer.parseInt(request.getParameter("id_turma")));
				
				turmaSelecionada.setMatriculas(matriculaDAO.getByTurma(turmaSelecionada));
				
				request.setAttribute("turma", turmaSelecionada);

				destino = "/matricula/alunos_turma.jsp";
				break;
				
			case 7: // Turmas do aluno
				alunoSelecionado = alunoDAO.getById(Integer.parseInt(request.getParameter("id_aluno")));
				
				alunoSelecionado.setMatriculas(matriculaDAO.getByAluno(alunoSelecionado));
				
				request.setAttribute("aluno", alunoSelecionado);
				
				destino = "/matricula/turmas_aluno.jsp";
				break;
				
			default:
				request.setAttribute("listaMatriculas", matriculas);

			}


		} catch (Exception e) {
			mensagem = "Erro!";
			destino = "erro.jsp";
			e.printStackTrace();
		}

		request.setAttribute("mensagem", mensagem);
		RequestDispatcher rd = request.getRequestDispatcher(destino);
		rd.forward(request, response);
	}

}
