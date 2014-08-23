package br.com.senac.dao;

import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;
import javax.xml.bind.annotation.XmlRootElement;

import br.com.senac.model.Curso;

@Stateless
@Local
public class CursoDAO {
	
	@PersistenceContext
	EntityManager em;
	
	public boolean inserir(Curso curso) {
		em.persist(curso);
		return true;
	}

	public boolean atualizar(Curso curso) {
		em.merge(curso);
		return true;
	}
	
	public boolean apagar(Curso curso) {
		curso = getById(curso.getId());
		if (curso != null) {
			em.remove(curso);
		}
		return true;
	}

	public Curso getById(int id) {
		return em.find(Curso.class, id);
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List<Curso> listar() {
		CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
		cq.select(cq.from(Curso.class));
		return (List<Curso>) em.createQuery(cq).getResultList();
	}

	public List<Curso> buscar(String texto) {
		texto = "%" + texto + "%";
		
		Query query = em.createNamedQuery("buscarCursos");
		query.setParameter("nome", texto);
		query.setMaxResults(10);
		
		List<Curso> cursos = (List<Curso>) query.getResultList();
		
		return cursos;
	}	
	
}
