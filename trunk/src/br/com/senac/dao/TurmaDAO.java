package br.com.senac.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.exception.ConstraintViolationException;
import org.hibernate.transform.Transformers;

import br.com.senac.model.Aluno;
import br.com.senac.model.Professor;
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
	
	public Turma getByNome(String nome) {
		Turma turma = new Turma();
		try {
			session = Conexao.getSession();
			session.beginTransaction();
			turma = (Turma) session.createCriteria(Turma.class)
					.add( Restrictions.naturalId()
							.set("nome", nome)
					).setCacheable(true)
					.uniqueResult();
			session.getTransaction().commit();
		} catch (Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();
		}finally{
			session.close();
		}
		return turma;
	}	
	
	public Turma addProfessor(Turma t, Professor professor) {
		Turma turma = new Turma();
		try {
			session = Conexao.getSession();
			session.beginTransaction();
			turma = (Turma) session.get(Turma.class, t.getId());
			turma.addProfessor(professor);
			session.getTransaction().commit();
		} catch (Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();
		}finally{
			session.close();
		}
		return turma;		
	}
	
	public List<Turma> listar() {
		List<Turma> turmas = new ArrayList<>();
		session = Conexao.getSession();
		try {
			session.beginTransaction();
			turmas = session.createCriteria(Turma.class)
					.setProjection(Projections.distinct(Projections.projectionList()
							.add(Projections.property("id"), "id")
							.add(Projections.property("nome"), "nome")
							.add(Projections.property("dataInicio"), "dataInicio")
							.add(Projections.property("dataFim"), "dataFim")
							.add(Projections.property("previsaoTermino"), "previsaoTermino")
							.add(Projections.property("curso"), "curso")))
					.setResultTransformer(Transformers.aliasToBean(Turma.class))
					.list();
			session.getTransaction().commit();
		} catch(Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return turmas;
	}
	
	public List<Aluno> listarAlunos(Turma turma) {
		List<Aluno> alunos = new ArrayList<>();
		session = Conexao.getSession();
		try {
			session.beginTransaction();
			Criteria cri = session.createCriteria(Aluno.class)
					.setProjection(Projections.distinct(Projections.projectionList()
							.add(Projections.property("nome"), "nome")))
					.setResultTransformer(Transformers.aliasToBean(Aluno.class))
					.createCriteria("turmas", "t")
					.add(Restrictions.eq("id", turma.getId()));
					
			alunos = cri.list();
			session.getTransaction().commit();
		} catch(Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return alunos;
	}

	public List<Professor> listarProfessores(Turma turma) {
		List<Professor> professores = new ArrayList<>();
		session = Conexao.getSession();
		try {
			session.beginTransaction();
			Criteria cri = session.createCriteria(Professor.class)
					.setProjection(Projections.distinct(Projections.projectionList()
							.add(Projections.property("nome"), "nome")))
					.setResultTransformer(Transformers.aliasToBean(Professor.class))
					.createCriteria("turmas", "t")
					.add(Restrictions.eq("id", turma.getId()));
					
			professores = cri.list();
			session.getTransaction().commit();
		} catch(Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return professores;
	}
	
	
/*	public ArrayList<Aluno> listarAlunos(int id) {
		conn = Conexao.getConexao();
		
		ArrayList<Aluno> alunos = new ArrayList<>();
		PreparedStatement pstmt = null;
		
		try {
			pstmt = conn.prepareStatement("select a.id, a.nome, a.sobrenome, "
					+ "a.email, a.matricula, a.bolsa "
					+ "from turma_aluno ta "
					+ "join aluno a on a.id = ta.id_aluno "
					+ "where ta.id_turma = ?");
			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();
			
			while (rs.next()) {
				alunos.add(null); /*new Aluno(rs.getInt("id"), 
						rs.getString("nome"),
						rs.getString("sobrenome"), 
						rs.getString("email"), 
						rs.getInt("matricula"), 
						rs.getBoolean("bolsa")));*/
/*			}
			
			rs.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return alunos;
	}

	public ArrayList<Professor> listarProfessores(int id) {
		conn = Conexao.getConexao();
		
		ArrayList<Professor> professores = new ArrayList<>();
		PreparedStatement pstmt = null;
		
		try {
			pstmt = conn.prepareStatement("select p.id, p.nome, p.sobrenome, "
					+ "p.email, p.especialidade, p.salario, p.vinculo "
					+ "from turma_professor tp "
					+ "join professor p on p.id = tp.id_professor "
					+ "where tp.id_turma = ?");
			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();
			
			while (rs.next()) {
				professores.add(null); /*new Professor(rs.getInt("id"), 
						rs.getString("nome"),
						rs.getString("sobrenome"), 
						rs.getString("email"),
						rs.getString("especialidade"),
						rs.getDouble("salario"), 
						rs.getString("vinculo")));*/
/*			}
			
			rs.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return professores;
	}

	public void vincular(int id, ArrayList<Aluno> alunos, ArrayList<Professor> professores) {
		conn = Conexao.getConexao();

		PreparedStatement pstmtAluno = null;
		PreparedStatement pstmtProfessor = null;
		PreparedStatement pstmtDelAluno = null;
		PreparedStatement pstmtDelProfessor = null;
		
		try {
			pstmtDelAluno = conn.prepareStatement("DELETE FROM turma_aluno WHERE id_turma = ?");
			pstmtDelAluno.setInt(1, id);
			pstmtDelAluno.execute();
			
			for (Aluno a : alunos) {
				pstmtAluno = conn.prepareStatement("INSERT INTO turma_aluno (id_turma, id_aluno)"
						+ " values(?,?)");
				pstmtAluno.setInt(1, id);
				pstmtAluno.setInt(2, a.getId());
				pstmtAluno.execute();
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmtDelAluno != null) {
					pstmtDelAluno.close();
				}
				if (pstmtAluno != null) {
					pstmtAluno.close();
				}
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		conn = Conexao.getConexao();
		
		try {
			pstmtDelProfessor = conn.prepareStatement("DELETE FROM turma_professor WHERE id_turma = ?");
			pstmtDelProfessor.setInt(1, id);
			pstmtDelProfessor.execute();
			
			for (Professor a : professores) {
				pstmtProfessor = conn.prepareStatement("INSERT INTO turma_professor (id_turma, id_professor)"
						+ " values(?,?)");
				pstmtProfessor.setInt(1, id);
				pstmtProfessor.setInt(2, a.getId());
				pstmtProfessor.execute();
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmtDelProfessor != null) {
					pstmtDelProfessor.close();
				}
				if (pstmtProfessor != null) {
					pstmtProfessor.close();
				}
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}		
	}*/
}
