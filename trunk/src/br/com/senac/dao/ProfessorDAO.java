package br.com.senac.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;

import org.hibernate.Session;

import br.com.senac.model.Professor;
import br.com.senac.model.Professor;

@Stateless
@Local
public class ProfessorDAO {

	@PersistenceContext
	EntityManager em;
	
	public boolean inserir(Professor professor) {
		em.persist(professor);
		return true;
	}
	
	public boolean atualizar(Professor professor) {
		em.merge(professor);
		return true;
	}

	public boolean apagar(Professor professor) {
		professor = getById(professor.getId());
		if (professor != null) {
			em.remove(professor);
		}
		return true;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List<Professor> listar() {
		CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
		cq.select(cq.from(Professor.class));
		return (List<Professor>) em.createQuery(cq).getResultList();
	}
	
	public Professor getById(int id) {
		return em.find(Professor.class, id);
	}
	
	public List<Professor> buscar(String texto) {
		texto = "%" + texto + "%";
		
		Query query = em.createNamedQuery("buscarProfessores");
		query.setParameter("nome", texto);
		query.setParameter("sobrenome", texto);
		query.setParameter("email", texto);
		query.setMaxResults(10);
		
		List<Professor> professores = query.getResultList();
		
		return professores;
	}

}
