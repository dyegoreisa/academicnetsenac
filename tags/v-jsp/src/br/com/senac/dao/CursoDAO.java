package br.com.senac.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import br.com.senac.model.Aluno;
import br.com.senac.model.Curso;

public class CursoDAO {
	
	public Session session;
	
	public boolean inserir(Curso curso) {
		boolean resp = false;
		try {
			session = Conexao.getSession();
			session.beginTransaction();
			session.save(curso);
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

	public boolean atualizar(Curso curso) {
		try {
			session = Conexao.getSession();
			session.beginTransaction();
			session.update(curso);
			session.getTransaction().commit();
		} catch (Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return false;
	}
	
	public boolean apagar(Curso curso) {
		boolean resp = false;
		try {
			session = Conexao.getSession();
			session.beginTransaction();
			session.delete(curso);
			session.getTransaction().commit();
			resp = true;
		} catch(Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return resp;
	}

	public Curso getById(int id) {
		Curso curso = new Curso();
		try {
			session = Conexao.getSession();
			session.beginTransaction();
			curso = (Curso) session.get(Curso.class, id);
			session.getTransaction().commit();
		} catch (Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();
		}finally{
			session.close();
		}
		return curso;
	}
	
	public ArrayList<Curso> listar() {
		ArrayList<Curso> cursos = new ArrayList();
		session = Conexao.getSession();
		try {
			session.beginTransaction();
			cursos = (ArrayList<Curso>) session.createCriteria(Curso.class).list();
			session.getTransaction().commit();
		} catch(Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return cursos;
	}

	public List<Curso> buscar(String texto) {
		List<Curso> cursos = null;
		try {
			session = Conexao.getSession();
			session.beginTransaction();
			cursos = (List<Curso>) session.createCriteria(Curso.class)
					.add( Restrictions.like("nome", "%" + texto + "%") )
					.list();
			session.getTransaction().commit();
		} catch (Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return cursos;
	}	
	
}
