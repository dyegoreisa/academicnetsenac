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

import br.com.senac.model.Disciplina;
import br.com.senac.model.Disciplina;
import br.com.senac.model.Disciplina;

@Stateless
@Local
public class DisciplinaDAO {
	
	@PersistenceContext
	EntityManager em;
	
	public boolean inserir(Disciplina disciplina) {
		em.persist(disciplina);
		return true;
	}

	public boolean atualizar(Disciplina disciplina) {
		em.merge(disciplina);
		return true;
	}
	
	public boolean apagar(Disciplina disciplina) {
		disciplina = getById(disciplina.getId());
		if (disciplina != null) {
			em.remove(disciplina);
		}
		return true;
	}

	public Disciplina getById(int id) {
		return em.find(Disciplina.class, id);
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List<Disciplina> listar() {
		CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
		cq.select(cq.from(Disciplina.class));
		return (List<Disciplina>) em.createQuery(cq).getResultList();
	}

	public List<Disciplina> buscar(String texto) {
		texto = "%" + texto + "%";
		
		Query query = em.createNamedQuery("buscarDisciplinas");
		query.setParameter("nome", texto);
		query.setMaxResults(10);
		
		List<Disciplina> disciplinas = query.getResultList();
		
		return disciplinas;
	}
	
	public List<Disciplina> getListByIds(Integer[] idsDisciplinas) {
		// TODO Auto-generated method stub
		return null;
	}

}
