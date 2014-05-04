package br.com.senac.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.senac.dao.CursoDAO;
import br.com.senac.dao.TurmaDAO;
import br.com.senac.model.Curso;
import br.com.senac.model.Turma;


/**
 * Servlet implementation class TurmaServlet
 */
@WebServlet("/TurmaServlet")
public class TurmaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public TurmaServlet() {
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
		
		Turma turma = new Turma();
		Turma turmaEditar = new Turma();
		TurmaDAO dao = new TurmaDAO();
		CursoDAO cursoDAO = new CursoDAO();
		List<Turma> turmas = new ArrayList();
		
		String mensagem = "";
		String destino = "turma/turma_listar.jsp";
		
		List<Curso> cursos = cursoDAO.listar();
		request.setAttribute("listaCursos", cursos);		
		
		int acao = 0;
		
		try {

			if (request.getParameter("acao") != null) {
				acao = Integer.parseInt(request.getParameter("acao"));
			} else if (request.getParameter("id") != null) {
				turmaEditar = dao.getById(Integer.parseInt(request.getParameter("id")));
				acao = 4;
			}

			switch(acao)
			{
			case 1: // Inserir
				turma.setNome(request.getParameter("nome"));
				turma.setDataInicio(request.getParameter("data_inicio"));
				turma.setDataFim(request.getParameter("data_fim"));
				turma.setPrevisaoTermino(request.getParameter("previsao"));
				
				Curso cursoInserir = cursoDAO.getById(Integer.parseInt(request.getParameter("curso")));
				turma.setCurso(cursoInserir);
				
				dao.inserir(turma);

				mensagem = "Turma criado";
				
				turmas = dao.listar();
				request.setAttribute("listaTurmas", turmas);
				break;
				
			case 2: // Atualizar
				Curso cursoAlterar = cursoDAO.getById(Integer.parseInt(request.getParameter("curso")));
				turma.setCurso(cursoAlterar);
				
				turma.setId(Integer.parseInt(request.getParameter("id")));
				turma.setNome(request.getParameter("nome"));
				turma.setDataInicio(request.getParameter("data_inicio"));
				turma.setDataFim(request.getParameter("data_fim"));
				turma.setPrevisaoTermino(request.getParameter("previsao"));
				
				dao.atualizar(turma);
				
				mensagem = "Turma alterado";

				turmas = dao.listar();
				request.setAttribute("listaTurmas", turmas);
				break;

			case 3: // Excluir
				turma.setId(Integer.parseInt(request.getParameter("id")));
				
				dao.apagar(turma);
				
				mensagem = "Turma apagado";

				turmas = dao.listar();
				request.setAttribute("listaTurmas", turmas);
				break;
				
			case 4: // Tela de alterar
				int turmaId = Integer.parseInt(request.getParameter("id"));
				turmaEditar = dao.getById(turmaId);
				
				request.setAttribute("turmaEditar", turmaEditar);
				
				destino = "/turma/turma_cadastrar.jsp";
				break;
				
			case 5: // Buscar
				String conteudo = request.getParameter("conteudo");
				int cursoId = Integer.parseInt(request.getParameter("curso"));
				Curso cursoBusca = cursoDAO.getById(cursoId);
				
				turmas = dao.buscar(conteudo, cursoBusca);
				
				request.setAttribute("cursoBusca", cursoBusca);
				request.setAttribute("listaTurmas", turmas);
				break;
				
			case 6: // Tela de inclusão
				
				destino = "/turma/turma_cadastrar.jsp";
				break;
				
			default:
				turmas = dao.listar();
				request.setAttribute("listaTurmas", turmas);

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
