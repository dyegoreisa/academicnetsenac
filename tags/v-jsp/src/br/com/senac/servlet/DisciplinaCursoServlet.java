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

import br.com.senac.dao.CursoDAO;
import br.com.senac.dao.DisciplinaDAO;
import br.com.senac.dao.MatriculaDAO;
import br.com.senac.model.Curso;
import br.com.senac.model.Disciplina;
import br.com.senac.model.Matricula;

/**
 * Servlet implementation class DisciplinaCursoServlet
 */
@WebServlet("/DisciplinaCursoServlet")
public class DisciplinaCursoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DisciplinaCursoServlet() {
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
		MatriculaDAO matriculaDAO2 = new MatriculaDAO();
		List<Matricula> matriculas = new ArrayList();
		
		Disciplina disciplina = new Disciplina();
		DisciplinaDAO disciplinaDAO = new DisciplinaDAO();
		
		Curso curso = new Curso();
		CursoDAO cursoDAO = new CursoDAO();
		
		Curso cursoSelecionada = null;
		Disciplina disciplinaSelecionado = null;
						
		String mensagem = "";
	//	String destino = "matricula/matricular.jsp";
		String destino = "disciplina/disciplina.jsp";
		
		int acao = 0;
		
		try {

			if (request.getParameter("acao") != null) {
				acao = Integer.parseInt(request.getParameter("acao"));
			}

			switch(acao)
			{
			case 1: // Inserir

				cursoSelecionada = cursoDAO.getById(Integer.parseInt(request.getParameter("curso")));
				
				String[] idsdisciplinasStr = request.getParameterValues("disciplinas");
				Integer[] idsDisciplinas = new Integer[idsdisciplinasStr.length];
				
				for(int i = 0; i < idsdisciplinasStr.length; i++){
					idsDisciplinas[i] = Integer.parseInt(idsdisciplinasStr[i]);
				}
				
				List<Disciplina> disciplinasSelecionados = disciplinaDAO.getListByIds(idsDisciplinas);
				
				for(Disciplina item : disciplinasSelecionados) {
					StringBuffer sbCodigo = new StringBuffer();
					
					DateFormat dateFormatYear = new SimpleDateFormat("yyyy");
					Date date = new Date();
					
					sbCodigo.append(dateFormatYear.format(date));
					sbCodigo.append(item.getNome().substring(0, 2).toUpperCase());
			//		sbCodigo.append(item.getSobrenome().substring(0, 2).toUpperCase());
					sbCodigo.append(1000 + (int)(Math.random() * ((9999 - 1000) + 1)));
					
					
								
//					matriculaDAO2.inserir(new Matricula(cursoSelecionada, id, nome, true));
				}
				
				mensagem = "Disciplina inclu�da";
				destino = "sucesso.jsp";
				request.setAttribute("listaMatriculas", matriculas);
				break;
				
			case 2: // Atualizar
				break;

			case 3: // Excluir
				cursoSelecionada = cursoDAO.getById(Integer.parseInt(request.getParameter("id_curso")));
				disciplinaSelecionado = disciplinaDAO.getById(Integer.parseInt(request.getParameter("id_disciplina")));
				
//				matriculaDAO2.apagar(new Matricula(cursoSelecionada, disciplinaSelecionado));
				
				mensagem = "Matricula cancelada!";
							
				if (request.getParameter("destino").toString().equals("disciplina")) {
					disciplinaSelecionado.setMatriculas(matriculaDAO2.getByDisciplina(disciplinaSelecionado));
					request.setAttribute("disicplina", disciplinaSelecionado);
				//	destino = "/matricula/turmas_aluno.jsp";
					destino = "/disciplina/curso_disciplina.jsp";
					
				} else if (request.getParameter("destino").toString().equals("curso")) {
//					cursoSelecionada.setMatriculas(matriculaDAO2.getById(cursoSelecionada));
					request.setAttribute("curso", cursoSelecionada);
				//	destino = "/matricula/alunos_turma.jsp";
					destino = "/disciplina/curso_disciplina.jsp";
				}
				
				break;
				
			case 4: // Tela matriculas aluno na turma
				List<Disciplina> disciplinas = disciplinaDAO.listar();
				List<Curso> turmas = cursoDAO.listar();

				request.setAttribute("listaCursos", curso);				
				request.setAttribute("listaDisciplinas", disciplinas);
				
			//	destino = "/matricula/matricular_aluno_turma.jsp";
				destino = "/disciplina/incluir_disciplina_curso.jsp";
				break;
				
			case 5: // 
				break;
				
			case 6: // Alunos da turma
				cursoSelecionada = cursoDAO.getById(Integer.parseInt(request.getParameter("id_curso")));
				
//				cursoSelecionada.setMatriculas(matriculaDAO2.getById(cursoSelecionada));
				
				request.setAttribute("curso", cursoSelecionada);

			//	destino = "/matricula/alunos_turma.jsp";
				destino = "/disciplina/curso_disciplina.jsp";				
				break;
				
			case 7: // Turmas do aluno
				disciplinaSelecionado = disciplinaDAO.getById(Integer.parseInt(request.getParameter("id_disciplina")));
				
				disciplinaSelecionado.setMatriculas(matriculaDAO2.getByDisciplina(disciplinaSelecionado));
				
				request.setAttribute("disciplina", disciplinaSelecionado);
				
			//	destino = "/matricula/turmas_aluno.jsp";
				destino = "/disciplina/curso_disciplina.jsp";
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
