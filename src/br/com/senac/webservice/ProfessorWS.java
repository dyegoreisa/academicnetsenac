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

import br.com.senac.dao.ProfessorDAO;
import br.com.senac.model.Professor;

@WebService
@Path(value = "/ProfessorServico")
public class ProfessorWS {
	
	@EJB
	private ProfessorDAO professorDAO;
	
	public  ProfessorWS(){}

	@GET	
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/{id}")
	public Professor consultar(@PathParam("id") Integer id){
		return professorDAO.getById(id);
	}

	@PUT
	@Produces({MediaType.APPLICATION_FORM_URLENCODED,MediaType.APPLICATION_JSON})
	public String incluir(@FormParam("dataNascimento") String dataNascimento,
			@FormParam("email") String email,
			@FormParam("nome") String nome,
			@FormParam("sobrenome") String sobrenome,
			@FormParam("sexo") String sexo,
			@FormParam("especialidade") String especialidade,
			@FormParam("vinculo") String vinculo) {

		Professor professor = new Professor();
		
		professor.setDataNascimento(dataNascimento);
		professor.setEmail(email);
		professor.setNome(nome);
		professor.setSobrenome(sobrenome);
		professor.setSexo(sexo);
		professor.setEspecialidade(especialidade);
		professor.setVinculo(vinculo);
		
		professorDAO.inserir(professor);
		
		return professor.toString();
	}
	
	@POST
	@Produces({MediaType.APPLICATION_FORM_URLENCODED,MediaType.APPLICATION_JSON})
	public String alterar(@FormParam("dataNascimento") String dataNascimento,
			@FormParam("email") String email,
			@FormParam("id") Integer id,
			@FormParam("nome") String nome,
			@FormParam("sobrenome") String sobrenome,
			@FormParam("sexo") String sexo) {

		Professor professor = new Professor();
		
		professor.setDataNascimento(dataNascimento);
		professor.setEmail(email);
		professor.setId(id);
		professor.setNome(nome);
		professor.setSobrenome(sobrenome);
		professor.setSexo(sexo);
				
		professorDAO.atualizar(professor);
		
		return professor.toString();
	}

	@DELETE
	@Path("/{id}")
	@Produces({MediaType.APPLICATION_FORM_URLENCODED,MediaType.APPLICATION_JSON})
	public Integer excluir(@PathParam("id") Integer id) {
		Professor professor = professorDAO.getById(id);
		professorDAO.apagar(professor);
		return id;
	}

	@GET	
	@Produces(MediaType.APPLICATION_JSON)
	public List<Professor> lista() {
		return professorDAO.listar();
	}
	
}