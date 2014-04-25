package br.com.senac.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.hibernate.exception.ConstraintViolationException;

import br.com.senac.model.Curso;
import br.com.senac.model.Turma;

public class TurmaDAO {
	
	public Session session;

	public boolean inserir(Turma turma) {
		try {
			session = Conexao.getSession();
			session.beginTransaction();
			session.save(turma);
			session.getTransaction().commit();
		} catch (ConstraintViolationException e) {
			session.getTransaction().rollback();
			System.out.println(e.getSQL());
			System.out.println(e.getSQLException());
		} catch (Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return false;
	}

	public boolean atualizar(Turma turma) {
		try {
			session = Conexao.getSession();
			session.beginTransaction();
			session.update(turma);
			session.getTransaction().commit();
		} catch (Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return false;
	}
	
	public boolean apagar(Turma turma) {
		try {
			session = Conexao.getSession();
			session.beginTransaction();
			session.delete(turma);
			session.getTransaction().commit();
		} catch(Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return false;
	}

	public Turma getById(int id) {
		Turma turma = new Turma();
		try {
			session = Conexao.getSession();
			session.beginTransaction();
			turma = (Turma) session.get(Turma.class, id);
			session.getTransaction().commit();
		} catch (Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();
		}finally{
			session.close();
		}
		return turma;
	}
	
	public ArrayList<Turma> listar() {
		ArrayList<Turma> turmas = new ArrayList();
		session = Conexao.getSession();
		try {
			session.beginTransaction();
			turmas = (ArrayList<Turma>) session.createCriteria(Turma.class).list();
			session.getTransaction().commit();
		} catch(Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return turmas;
	}

	public List<Turma> buscar(String texto, Curso curso) {
		List<Turma> turmas = null;
		try {
			session = Conexao.getSession();
			session.beginTransaction();
			if (texto != null) {
				if (curso != null) {
					turmas = (List<Turma>) session.createCriteria(Turma.class)
							.add( Restrictions.like("nome", "%" + texto + "%") )
							.add( Restrictions.eq("curso", curso) )
							.list();
				} else {
					turmas = (List<Turma>) session.createCriteria(Turma.class)
							.add( Restrictions.like("nome", "%" + texto + "%") )
							.list();
				}
			} else if (curso != null) {
				turmas = (List<Turma>) session.createCriteria(Turma.class)
						.add( Restrictions.eq("curso", curso) )
						.list();
			}

			session.getTransaction().commit();
		} catch (Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return turmas;
	}
}
