package br.com.senac.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;

import br.com.senac.model.Usuario;

public class UsuarioDAO {
	
	private Session session;		
			
	public boolean inserir(Usuario usuario) {
		boolean resp = false;
		try {
			session = Conexao.getSession();
			session.beginTransaction();
			session.save(usuario);
			session.getTransaction().commit();
			resp = true;
		} catch (Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return resp;
	}

	public boolean atualizar(Usuario usuario) {
		boolean resp = false;
		try {
			session = Conexao.getSession();
			session.beginTransaction();
			session.update(usuario);
			session.getTransaction().commit();
			resp = true;
		} catch (Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return resp;
	}

	public boolean apagar(Usuario usuario) {
		boolean resp = false;
		try {
			session = Conexao.getSession();
			session.beginTransaction();
			session.delete(usuario);
			session.getTransaction().commit();
			resp = true;
		} catch (Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return resp;
	}

	public List<Usuario> listar() {
		List<Usuario> usuarios = new ArrayList();
		session = Conexao.getSession();
		try {
			session.beginTransaction();
			usuarios = session.createCriteria(Usuario.class).list();
			session.getTransaction().commit();
		} catch (Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return usuarios;
	}
	
	public Usuario getById(int id) {
		Usuario usuario = new Usuario();
		try {
			session = Conexao.getSession();
			session.beginTransaction();
			usuario = (Usuario) session.get(Usuario.class, id);
			session.getTransaction().commit();
		} catch (Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return usuario;
	}
	
	public List<Usuario> buscar(String texto) {
		List<Usuario> usuarios = null;
		try {
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
		}
		return usuarios;
	}
	
	public boolean verificarAcesso(Usuario usuario) {
		Usuario usuarioBanco = null;
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
			System.out.println("Erro ao verificar login e senha de usuário");
			e.printStackTrace();
		} finally {
			session.close();
		}
		return (usuarioBanco != null);
	}
}


