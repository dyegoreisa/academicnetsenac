package br.com.senac.dao;

import java.util.ArrayList;

import org.hibernate.Session;

import br.com.senac.model.Disciplina;

public class DisciplinaDAO {
	
	public Session session;
	
	public boolean inserir(Disciplina disciplina) {
		try {
			session = Conexao.getSession();
			session.beginTransaction();
			session.save(disciplina);
			session.getTransaction().commit();
		} catch (Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return false;
	}

	public boolean atualizar(Disciplina disciplina) {
		try {
			session = Conexao.getSession();
			session.beginTransaction();
			session.update(disciplina);
			session.getTransaction().commit();
		} catch (Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return false;
	}
	
	public boolean apagar(Disciplina disciplina) {
		try {
			session = Conexao.getSession();
			session.beginTransaction();
			session.delete(disciplina);
			session.getTransaction().commit();
		} catch(Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return false;
	}

	public Disciplina getById(int id) {
		Disciplina disciplina = new Disciplina();
		try {
			session = Conexao.getSession();
			session.beginTransaction();
			disciplina = (Disciplina) session.get(Disciplina.class, id);
			session.getTransaction().commit();
		} catch (Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();
		}finally{
			session.close();
		}
		return disciplina;
	}
	
	public ArrayList<Disciplina> listar() {
		ArrayList<Disciplina> disciplinas = new ArrayList();
		session = Conexao.getSession();
		try {
			session.beginTransaction();
			disciplinas = (ArrayList<Disciplina>) session.createCriteria(Disciplina.class).list();
			session.getTransaction().commit();
		} catch(Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return disciplinas;
	}

}
