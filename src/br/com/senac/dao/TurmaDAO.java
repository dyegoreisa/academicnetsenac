package br.com.senac.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import br.com.senac.model.Aluno;
import br.com.senac.model.Professor;
import br.com.senac.model.Turma;

public class TurmaDAO {

	private Connection conn;
	
	public boolean inserir(Turma t) {
		conn = Conexao.getConexao();

		boolean resp = false;
		PreparedStatement pstmt = null;
		
		try {
			pstmt = conn.prepareStatement("INSERT INTO \"turma\"(\"nome\") values(?)");
			pstmt.setString(1, t.getNome());
			resp = pstmt.execute();
			
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
		
		return resp;
	}

	public boolean atualizar(Turma p) {
		conn = Conexao.getConexao();
		
		boolean resp = false;
		PreparedStatement pstmt = null;
		
		try {
			pstmt = conn.prepareStatement("UPDATE \"turma\" SET \"nome\" = ? WHERE \"id\" = ?");
			pstmt.setString(1, p.getNome());
			pstmt.setInt(2, p.getId());
			resp = pstmt.execute();
			
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
		return resp;
	}

	public boolean apagar(Turma d) {
		conn = Conexao.getConexao();
		
		boolean resp = false;
		PreparedStatement pstmt = null;
		
		try {
			pstmt = conn.prepareStatement("DELETE FROM \"turma\" WHERE \"id\" = ?");
			pstmt.setInt(1, d.getId());
			resp = pstmt.execute();
			
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
		return resp;
	}

	public ArrayList<Turma> listar() {
		conn = Conexao.getConexao();
		
		ArrayList<Turma> Turmaes = new ArrayList<>();
		Statement stmt = null;
		
		try {
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT \"id\", \"nome\" FROM \"turma\"");
			
			while (rs.next()) {
				Turmaes.add(new Turma(rs.getInt("id"), rs.getString("nome")));
			}
			
			rs.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (stmt != null) {
					stmt.close();
				}
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return Turmaes;
	}
	
	public Turma getById(int id) {
		conn = Conexao.getConexao();
		
		Turma Turma = null;
		PreparedStatement pstmt =  null;
		
		try {
			pstmt = conn.prepareStatement("SELECT \"id\", \"nome\" FROM \"turma\" WHERE \"id\" = ?");
			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();
			
			rs.next();
			Turma = new Turma(rs.getInt("id"), 
					rs.getString("nome"));
			
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
		return Turma;
	}

	public ArrayList<Aluno> listarAlunos(int id) {
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
				alunos.add(new Aluno(rs.getInt("id"), 
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
				professores.add(new Professor(rs.getInt("id"), 
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
	}
}
