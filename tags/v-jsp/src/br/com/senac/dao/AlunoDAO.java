package br.com.senac.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import br.com.senac.model.Aluno;

public class AlunoDAO {
	
	private Session session;		
			
	public boolean inserir(Aluno aluno) {
		boolean resp = false;
		try {
			session = Conexao.getSession();
			session.beginTransaction();
			session.save(aluno);
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

	public boolean atualizar(Aluno aluno) {
		boolean resp = false;
		try {
			session = Conexao.getSession();
			session.beginTransaction();
			session.update(aluno);
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

	public boolean apagar(Aluno aluno) {
		boolean resp = false;
		try {
			session = Conexao.getSession();
			session.beginTransaction();
			session.delete(aluno);
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

	public List<Aluno> listar() {
		List<Aluno> alunos = new ArrayList();
		session = Conexao.getSession();
		try {
			session.beginTransaction();
			alunos = session.createCriteria(Aluno.class).list();
			session.getTransaction().commit();
		} catch (Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return alunos;
	}
	
	public Aluno getById(int id) {
		Aluno aluno = new Aluno();
		try {
			session = Conexao.getSession();
			session.beginTransaction();
			aluno = (Aluno) session.get(Aluno.class, id);
			session.getTransaction().commit();
		} catch (Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return aluno;
	}
	
	public List<Aluno> buscar(String texto) {
		List<Aluno> alunos = null;
		try {
			session = Conexao.getSession();
			session.beginTransaction();
			alunos = (List<Aluno>) session.createCriteria(Aluno.class)
					.add( Restrictions.like("nome", "%" + texto + "%") )
					.list();
			session.getTransaction().commit();
		} catch (Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return alunos;
	}

	public List<Aluno> getListByIds(Integer[] idsAlunos) {
		List<Aluno> alunos = null;
		try {
			session = Conexao.getSession();
			session.beginTransaction();
			alunos = (List<Aluno>) session.createCriteria(Aluno.class)
					.add( Restrictions.in("id", idsAlunos) )
					.list();
			session.getTransaction().commit();
		} catch (Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return alunos;
	}	
}


