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
		Aluno alunoEditar = new Aluno();
		AlunoDAO dao = new AlunoDAO();
		List<Aluno> alunos = new ArrayList();
		
		String mensagem = "";
		String destino = "aluno/aluno_listar.jsp";
		
		int acao = 0;
		
		try {

			if (request.getParameter("acao") != null) {
				acao = Integer.parseInt(request.getParameter("acao"));
			} else if (request.getParameter("id") != null) {
				alunoEditar = dao.getById(Integer.parseInt(request.getParameter("id")));
				acao = 4;
			}

			switch(acao)
			{
			case 1: // Inserir
				aluno.setNome(request.getParameter("nome"));
				aluno.setSobrenome(request.getParameter("sobrenome"));
				aluno.setSexo(request.getParameter("sexo"));
				aluno.setEmail(request.getParameter("email"));
				aluno.setDataNascimento(request.getParameter("data_nascimento"));
				
				dao.inserir(aluno);

				mensagem = "Aluno criado";
				
				alunos = dao.listar();
				request.setAttribute("listaAlunos", alunos);
				break;
				
			case 2: // Atualizar
				aluno.setId(Integer.parseInt(request.getParameter("id")));
				aluno.setNome(request.getParameter("nome"));
				aluno.setSobrenome(request.getParameter("sobrenome"));
				aluno.setSexo(request.getParameter("sexo"));
				aluno.setEmail(request.getParameter("email"));
				aluno.setDataNascimento(request.getParameter("data_nascimento"));
				
				dao.atualizar(aluno);
				
				mensagem = "Aluno alterado";

				alunos = dao.listar();
				request.setAttribute("listaAlunos", alunos);
				break;

			case 3: // Excluir
				aluno.setId(Integer.parseInt(request.getParameter("id")));
				
				dao.apagar(aluno);
				
				mensagem = "Aluno apagado";

				alunos = dao.listar();
				request.setAttribute("listaAlunos", alunos);
				break;
				
			case 4: // Tela de alterar
				int alunoId = Integer.parseInt(request.getParameter("id"));
				alunoEditar = dao.getById(alunoId);
				request.setAttribute("alunoEditar", alunoEditar);
				destino = "/aluno/aluno_cadastrar.jsp";
				break;
				
			case 5: // Buscar
				String conteudo = request.getParameter("conteudo");
				
				alunos = dao.buscar(conteudo);
				
				request.setAttribute("listaAlunos", alunos);
				break;
				
			case 6:
				destino = "/aluno/aluno_cadastrar.jsp";
				break;
				
			default:
				alunos = dao.listar();
				request.setAttribute("listaAlunos", alunos);

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
