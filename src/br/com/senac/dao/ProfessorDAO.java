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
		List<Professor> professores = new ArrayList<>();
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
	
/*	public ArrayList<Aluno> listarAlunosByProfessor(int id) {
		conn = Conexao.getConexao();
		
		ArrayList<Aluno> alunos = new ArrayList<>();
		PreparedStatement pstmt = null;
		
		try {
			pstmt = conn.prepareStatement("select a.id, a.nome, a.sobrenome, "
					+ "a.email, a.matricula, a.bolsa "
					+ "from turma_professor tp "
					+ "join turma_aluno ta on ta.id_turma = tp.id_turma "
					+ "join aluno a on a.id = ta.id_aluno "
					+ "where tp.id_professor = ?");
			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();
			
			while (rs.next()) {
				alunos.add(null); /*new Aluno(rs.getInt("id"), 
						rs.getString("nome"),
						rs.getString("sobrenome"), 
						rs.getString("email"), 
						rs.getInt("matricula"), 
						rs.getBoolean("bolsa")));
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
		return alunos;
	}
	*/
}
