package br.com.senac.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import br.com.senac.model.Aluno;
import br.com.senac.model.Matricula;
import br.com.senac.model.Turma;

public class MatriculaDAO {
	
	private Session session;		
			
	public boolean inserir(Matricula matricula) {
		boolean resp = false;
		try {
			session = Conexao.getSession();
			session.beginTransaction();
			session.save(matricula);
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

	public boolean atualizar(Matricula matricula) {
		boolean resp = false;
		try {
			session = Conexao.getSession();
			session.beginTransaction();
			session.update(matricula);
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

	public boolean apagar(Matricula matricula) {
		boolean resp = false;
		try {
			session = Conexao.getSession();
			session.beginTransaction();
			session.delete(matricula);
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

	public List<Matricula> listar() {
		List<Matricula> matriculas = new ArrayList();
		session = Conexao.getSession();
		try {
			session.beginTransaction();
			matriculas = session.createCriteria(Matricula.class).list();
			session.getTransaction().commit();
		} catch (Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return matriculas;
	}
	
	public Matricula getById(int id) {
		Matricula matricula = null;
		try {
			session = Conexao.getSession();
			session.beginTransaction();
			matricula = (Matricula) session.get(Matricula.class, id);
			session.getTransaction().commit();
		} catch (Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return matricula;
	}
	
	public List<Matricula> buscar(String texto) {
		List<Matricula> matriculas = null;
		try {
			session = Conexao.getSession();
			session.beginTransaction();
			matriculas = (List<Matricula>) session.createCriteria(Matricula.class)
					.add( Restrictions.like("nome", "%" + texto + "%") )
					.list();
			session.getTransaction().commit();
		} catch (Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return matriculas;
	}
	
	public List<Matricula> getByTurma(Turma turma) {
		List<Matricula> matriculas = null;
		try {
			session = Conexao.getSession();
			session.beginTransaction();
			matriculas = (List<Matricula>) session.createCriteria(Matricula.class)
				    .createAlias("aluno", "a")
				    .add( Restrictions.eqProperty("aluno", "a.id") )
					.add( Restrictions.eq("turma", turma) )
					.list();
			session.getTransaction().commit();
		} catch (Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return matriculas;
	}
	
	public List<Matricula> getByAluno(Aluno aluno) {
		List<Matricula> matriculas = null;
		try {
			session = Conexao.getSession();
			session.beginTransaction();
			matriculas = (List<Matricula>) session.createCriteria(Matricula.class)
				    .createAlias("turma", "a")
				    .add( Restrictions.eqProperty("turma", "a.id") )
					.add( Restrictions.eq("aluno", aluno) )
					.list();
			session.getTransaction().commit();
		} catch (Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return matriculas;
	}
}


