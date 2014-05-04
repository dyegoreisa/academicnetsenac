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

import br.com.senac.dao.DisciplinaDAO;
import br.com.senac.model.Disciplina;


/**
 * Servlet implementation class DisciplinaServlet
 */
@WebServlet("/DisciplinaServlet")
public class DisciplinaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public DisciplinaServlet() {
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
		
		Disciplina Disciplina = new Disciplina();
		Disciplina DisciplinaEditar = new Disciplina();
		DisciplinaDAO dao = new DisciplinaDAO();
		List<Disciplina> Disciplinas = new ArrayList();
		
		String mensagem = "";
		String destino = "disciplina/disciplina_listar.jsp";
		
		int acao = 0;
		
		try {

			if (request.getParameter("acao") != null) {
				acao = Integer.parseInt(request.getParameter("acao"));
			} else if (request.getParameter("id") != null) {
				DisciplinaEditar = dao.getById(Integer.parseInt(request.getParameter("id")));
				acao = 4;
			}

			switch(acao)
			{
			case 1: // Inserir
				Disciplina.setNome(request.getParameter("nome"));

				dao.inserir(Disciplina);

				mensagem = "Disciplina criado";
				
				Disciplinas = dao.listar();
				request.setAttribute("listaDisciplinas", Disciplinas);
				break;
				
			case 2: // Atualizar
				Disciplina.setId(Integer.parseInt(request.getParameter("id")));
				Disciplina.setNome(request.getParameter("nome"));
				
				dao.atualizar(Disciplina);
				
				mensagem = "Disciplina alterado";

				Disciplinas = dao.listar();
				request.setAttribute("listaDisciplinas", Disciplinas);
				break;

			case 3: // Excluir
				Disciplina.setId(Integer.parseInt(request.getParameter("id")));
				
				dao.apagar(Disciplina);
				
				mensagem = "Disciplina apagado";

				Disciplinas = dao.listar();
				request.setAttribute("listaDisciplinas", Disciplinas);
				break;
				
			case 4: // Tela de alterar
				int DisciplinaId = Integer.parseInt(request.getParameter("id"));
				DisciplinaEditar = dao.getById(DisciplinaId);
				request.setAttribute("DisciplinaEditar", DisciplinaEditar);
				destino = "/disciplina/disciplina_cadastrar.jsp";
				break;
				
			case 5: // Buscar
				String conteudo = request.getParameter("conteudo");
				
				Disciplinas = dao.buscar(conteudo);
				
				request.setAttribute("listaDisciplinas", Disciplinas);
				break;
				
			case 6:
				destino = "/disciplina/disciplina_cadastrar.jsp";
				break;
				
			default:
				Disciplinas = dao.listar();
				request.setAttribute("listaDisciplinas", Disciplinas);

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
