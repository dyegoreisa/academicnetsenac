package br.com.senac.dao;

import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;

import org.hibernate.Session;

import br.com.senac.model.Aluno;

@Stateless
@Local
public class AlunoDAO {
	
	@PersistenceContext
	EntityManager em;

	public boolean inserir(Aluno aluno) {
		em.persist(aluno);
		return true;
	}
	
	public boolean atualizar(Aluno aluno) {
		em.merge(aluno);
		return true;
	}

	public boolean apagar(Aluno aluno) {
		aluno = getById(aluno.getId());
		if (aluno != null) {
			em.remove(aluno);
		}
		return true;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List<Aluno> listar() {
		CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
		cq.select(cq.from(Aluno.class));
		return (List<Aluno>) em.createQuery(cq).getResultList();
	}
	
	public Aluno getById(int id) {
		return em.find(Aluno.class, id);
	}
	
	public List<Aluno> buscar(String texto) {
		texto = "%" + texto + "%";
		
		Query query = em.createNamedQuery("buscarAlunos");
		query.setParameter("nome", texto);
		query.setParameter("sobrenome", texto);
		query.setParameter("email", texto);
		query.setMaxResults(10);
		
		List<Aluno> alunos = query.getResultList();
		
		return alunos;
	}

	public List<Aluno> getListByIds(Integer[] idsAlunos) {
		List<Aluno> alunos = null;
		// TODO: Fazer a busca por array de id de alunos
		return alunos;
	}
	
}


