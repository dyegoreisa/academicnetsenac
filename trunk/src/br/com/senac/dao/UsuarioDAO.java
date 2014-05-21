package br.com.senac.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaQuery;

import org.hibernate.Criteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;

import br.com.senac.model.Aluno;
import br.com.senac.model.Usuario;

@Stateless
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

	public boolean apagar(Usuario usuario) {
		boolean resp = false;
		// TODO: Verificar se o usu·rio existe
		em.remove(usuario);
		return resp;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List<Usuario> listar() {
		CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
		cq.select(cq.from(Aluno.class));
		return (List<Usuario>) em.createQuery(cq).getResultList();
	}
	
	public Usuario getById(int id) {
		Usuario usuario = new Usuario();
		/*try {
			session = Conexao.getSession();
			session.beginTransaction();
			usuario = (Usuario) session.get(Usuario.class, id);
			session.getTransaction().commit();
		} catch (Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}*/

		return (Usuario) em.createQuery("SELECT * FROM usuario u WHERE u.id = :id")
				.setParameter("id", id)
				.setMaxResults(1);
	}
	
	public List<Usuario> buscar(String texto) {
		List<Usuario> usuarios = null;
		/*try {
			session = Conexao.getSession();
			session.beginTransaction();
			usuarios = (List<Usuario>) session.createCriteria(Usuario.class)
					.add( Restrictions.like("nome", "%" + texto + "%") )
					.list();
			session.getTransaction().commit();
		} catch (Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}*/
		return usuarios;
	}
	
	public boolean verificarAcesso(Usuario usuario) {
		/*Usuario usuarioBanco = null;
		try {
			session = Conexao.getSession();
			session.beginTransaction();
			Criteria cri = session.createCriteria(Usuario.class)
					.setProjection(Projections.distinct(Projections.projectionList()
							.add(Projections.property("id"), "id")
							.add(Projections.property("login"), "login")))
					.setResultTransformer(Transformers.aliasToBean(Usuario.class))
					.add(Restrictions.eq("login", usuario.getLogin()))
					.add(Restrictions.eq("senha", usuario.getSenha()));
					
			usuarioBanco = (Usuario) cri.uniqueResult();
			session.getTransaction().commit();
		} catch (Exception e) {
			session.getTransaction().rollback();
			System.out.println("Erro ao verificar login e senha de usu√°rio");
			e.printStackTrace();
		} finally {
			session.close();
		}
		
		return (usuarioBanco != null);*/
		
		Object u = em.createQuery("SELECT id FROM Usuario u WHERE u.login = :login and u.senha = :senha")
				.setParameter("login", usuario.getLogin())
				.setParameter("senha", usuario.getSenha())
				.setMaxResults(1);
		
		return (u != null);
		
	}
}


