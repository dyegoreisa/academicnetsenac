package br.com.senac.dao;

import java.math.BigInteger;
import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.interceptor.Interceptors;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;

import br.com.senac.interceptor.UsuarioInterceptor;
import br.com.senac.model.Aluno;
import br.com.senac.model.Usuario;

@Stateless
@Local
@Interceptors(UsuarioInterceptor.class)
public class UsuarioDAO {
	
	@PersistenceContext
	EntityManager em;
			
	public boolean inserir(Usuario usuario) {
		boolean resp = false;
		em.persist(usuario);
		return resp;
	}

	public boolean atualizar(Usuario usuario) {
		boolean resp = false;
		em.merge(usuario);
		return resp;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List<Usuario> listar() {
		CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
		cq.select(cq.from(Aluno.class));
		return (List<Usuario>) em.createQuery(cq).getResultList();
	}
	
	public Usuario getById(int id) {
		return (Usuario) em.createQuery("SELECT * FROM usuario u WHERE u.id = :id")
				.setParameter("id", id)
				.setMaxResults(1);
	}
	
	public boolean verificarAcesso(Usuario usuario) {
		boolean result = false;
		try {
			Query query = em.createNamedQuery("verificarLoginSenhaUsuario");
			query.setParameter("login", usuario.getLogin());
			query.setParameter("senha", usuario.getSenha());
			Usuario usu = (Usuario) query.getSingleResult();
			
			result = (usu != null) ? true : false;
		} catch (NonUniqueResultException ex) {
			result = false;
			ex.printStackTrace();
		} catch (NoResultException ex) {
			result = false;
			ex.printStackTrace();
		}
		
		return result;
	}
}


