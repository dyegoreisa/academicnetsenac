package br.com.senac.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
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
		List<Matricula> matriculas = new ArrayList<>();
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
		Matricula matricula = new Matricula();
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

	public List<Aluno> listarAlunosByTurma(Turma turma) {
		List<Aluno> alunos = new ArrayList<>();
		try {
			session = Conexao.getSession();
			session.beginTransaction();
			//alunos = (List<Aluno>) session.createCriteria(Aluno.class).list();
			session.getTransaction().commit();
		} catch(Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return alunos;
	}
	
/*	public ArrayList<Professor> listarProfessoresByAluno(int id) {
		conn = Conexao.getConexao();
		
		ArrayList<Professor> professores = new ArrayList<>();
		PreparedStatement pstmt = null;
		
		try {
			pstmt = conn.prepareStatement("select p.id, p.nome, p.sobrenome, "
					+ "p.email, p.especialidade, p.salario, p.vinculo "
					+ "from turma_aluno ta "
					+ "join turma_professor tp on tp.id_turma = ta.id_turma "
					+ "join professor p on p.id = tp.id_professor "
					+ "where ta.id_aluno = ?");
			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();
			
			while (rs.next()) {
				professores.add(null);/*new Professor(rs.getInt("id"), 
						rs.getString("nome"),
						rs.getString("sobrenome"), 
						rs.getString("email"),
						rs.getString("especialidade"),
						rs.getDouble("salario"), 
						rs.getString("vinculo")));
			}
			
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
	}*/
}


