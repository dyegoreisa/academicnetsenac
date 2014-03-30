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
import br.com.senac.model.Curso;


/**
 * Servlet implementation class CursoServlet
 */
@WebServlet("/CursoServlet")
public class CursoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public CursoServlet() {
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
		
		Curso curso = new Curso();
		Curso cursoEditar = new Curso();
		CursoDAO dao = new CursoDAO();
		List<Curso> cursos = new ArrayList();
		
		String mensagem = "";
		String destino = "curso/curso_listar.jsp";
		
		int acao = 0;
		
		try {

			if (request.getParameter("acao") != null) {
				acao = Integer.parseInt(request.getParameter("acao"));
			} else if (request.getParameter("id") != null) {
				cursoEditar = dao.getById(Integer.parseInt(request.getParameter("id")));
				acao = 4;
			}

			switch(acao)
			{
			case 1: // Inserir
				curso.setNome(request.getParameter("nome"));

				dao.inserir(curso);

				mensagem = "Curso criado";
				
				cursos = dao.listar();
				request.setAttribute("listaCursos", cursos);
				break;
				
			case 2: // Atualizar
				curso.setId(Integer.parseInt(request.getParameter("id")));
				curso.setNome(request.getParameter("nome"));
				
				dao.atualizar(curso);
				
				mensagem = "Curso alterado";

				cursos = dao.listar();
				request.setAttribute("listaCursos", cursos);
				break;

			case 3: // Excluir
				curso.setId(Integer.parseInt(request.getParameter("id")));
				
				dao.apagar(curso);
				
				mensagem = "Curso apagado";

				cursos = dao.listar();
				request.setAttribute("listaCursos", cursos);
				break;
				
			case 4: // Tela de alterar
				int cursoId = Integer.parseInt(request.getParameter("id"));
				cursoEditar = dao.getById(cursoId);
				request.setAttribute("cursoEditar", cursoEditar);
				destino = "/curso/curso_cadastrar.jsp";
				break;
				
			case 5: // Buscar
				String conteudo = request.getParameter("conteudo");
				
				cursos = dao.buscar(conteudo);
				
				request.setAttribute("listaCursos", cursos);
				break;
				
			case 6:
				destino = "/curso/curso_cadastrar.jsp";
				break;
				
			default:
				cursos = dao.listar();
				request.setAttribute("listaCursos", cursos);

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
