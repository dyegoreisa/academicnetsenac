package br.com.senac.dao;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;

import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.hibernate.exception.ConstraintViolationException;

import br.com.senac.model.Turma;
import br.com.senac.model.Turma;

@Stateless
@Local
public class TurmaDAO {
	
	@PersistenceContext
	EntityManager em;

	public boolean inserir(Turma turma) {
		em.persist(turma);
		return true;
	}

	public boolean atualizar(Turma turma) {
		em.merge(turma);
		return true;
	}
	
	public boolean apagar(Turma turma) {
		turma = getById(turma.getId());
		if (turma != null) {
			em.remove(turma);
		}
		return true;
	}

	public Turma getById(int id) {
		return em.find(Turma.class, id);
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List<Turma> listar() {
		CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
		cq.select(cq.from(Turma.class));
		return (List<Turma>) em.createQuery(cq).getResultList();
	}

	public List<Turma> buscar(String texto) {
		texto = "%" + texto + "%";
		
		Query query = em.createNamedQuery("buscarTurmas");
		query.setParameter("nome", texto);
		query.setMaxResults(10);
		
		List<Turma> turmas = query.getResultList();
		
		return turmas;
	}

	public List<Turma> buscar(String texto, Turma turma) {
		List<Turma> turmas = null;
		// TODO: fazer busca com dois campos opcionais
		return turmas;
	}
	
}
