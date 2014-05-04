package br.com.senac.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import org.hibernate.Session;

import br.com.senac.model.Professor;

public class ProfessorDAO {

	private Session session;
	
	public boolean inserir(Professor professor) {
		session = Conexao.getSession();
		boolean resp = false;
		try {
			session.beginTransaction();
			session.save(professor);
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

	public boolean atualizar(Professor professor) {
		session = Conexao.getSession();
		boolean resp = true;
		try {
			session.beginTransaction();
			session.update(professor);
			session.getTransaction().commit();
			resp = true;
		} catch (Exception e) {
			session.beginTransaction().rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return resp;
	}

	public boolean apagar(Professor professor) {
		session = Conexao.getSession();
		boolean resp = true;
		try {
			session.beginTransaction();
			session.delete(professor);
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

	public List<Professor> listar() {
		List<Professor> professores = new ArrayList();
		session = Conexao.getSession();
		try {
			session.beginTransaction();
			professores = session.createCriteria(Professor.class).list();
			session.getTransaction().commit();
		} catch (Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return professores;
	}
	
	public Professor getById(int id) {
		Professor professor = null;
		session = Conexao.getSession();
		try {
			session.beginTransaction();
			professor = (Professor) session.get(Professor.class, id);
			session.getTransaction().commit();
		} catch (Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return professor;
	}
}
