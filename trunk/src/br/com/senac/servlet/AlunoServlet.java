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

import br.com.senac.dao.AlunoDAO;
import br.com.senac.model.Aluno;


/**
 * Servlet implementation class AlunoServlet
 */
@WebServlet("/AlunoServlet")
public class AlunoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public AlunoServlet() {
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
		
		Aluno aluno = new Aluno();
		AlunoDAO dao = new AlunoDAO();
		List<Aluno> alunos = new ArrayList();
		
		String sucessPage = "";
		
		try {
			int acao = 0;
			if (request.getParameter("acao") != null) {
				acao = Integer.parseInt(request.getParameter("acao"));
			}
			
			aluno.setNome(request.getParameter("nome"));
			aluno.setSobrenome(request.getParameter("sobrenome"));
			aluno.setSexo(request.getParameter("sexo"));
			aluno.setEmail(request.getParameter("email"));
			aluno.setDataNascimento(request.getParameter("data_nascimento"));
			
			switch(acao)
			{
			case 1:
				dao.inserir(aluno);
				sucessPage = "sucesso.jsp";
				break;
				
			case 2:
				dao.atualizar(aluno);
				sucessPage = "sucesso.jsp";
				break;

			case 3:
				dao.apagar(aluno);
				alunos = dao.listar();
				sucessPage = "aluno_listar.jsp";
				break;

			case 4:
				alunos = dao.listar();
				sucessPage = "aluno_listar.jsp";
				break;
				
			}


		} catch (Exception e) {
			e.printStackTrace();
		}
		
		request.setAttribute("aluno", aluno);
		RequestDispatcher rd = request.getRequestDispatcher(sucessPage);
		rd.forward(request, response);
		
	}

}
