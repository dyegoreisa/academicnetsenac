package br.com.senac.webservice;

import java.util.List;

import javax.ejb.EJB;
import javax.jws.WebService;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import br.com.senac.dao.CursoDAO;
import br.com.senac.model.Curso;

@WebService
@Path(value = "/CursoServico")
public class CursoWS {
	
	@EJB
	private CursoDAO cursoDAO;
	
	public  CursoWS(){}

	@GET	
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/{id}")
	public Curso consultar(@PathParam("id") Integer id){
		System.out.println(id);
		return cursoDAO.getById(id);
	}
	
	@PUT
	@Produces({MediaType.APPLICATION_FORM_URLENCODED,MediaType.APPLICATION_JSON})
	public String incluir(@FormParam("nome") String nome) {

		Curso curso = new Curso();
		
		curso.setNome(nome);
				
		cursoDAO.inserir(curso);
		
		return curso.toString();
	}
	
	@POST
	@Produces({MediaType.APPLICATION_FORM_URLENCODED,MediaType.APPLICATION_JSON})
	public String alterar(@FormParam("id") Integer id,
			@FormParam("nome") String nome) {

		Curso curso = new Curso();
		
		curso.setId(id);
		curso.setNome(nome);
				
		cursoDAO.atualizar(curso);
		
		return curso.toString();
	}

	@DELETE
	@Path("/{id}")
	@Produces({MediaType.APPLICATION_FORM_URLENCODED,MediaType.APPLICATION_JSON})
	public String excluir(@PathParam("id") Integer id) {
		Curso curso = cursoDAO.getById(id);
		cursoDAO.apagar(curso);
		return "ID: " + id + " excluido.";
	}

	@GET	
	@Produces(MediaType.APPLICATION_JSON)
	public List<Curso> lista() {
		return cursoDAO.listar();
	}
	
}