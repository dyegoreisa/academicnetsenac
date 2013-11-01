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

public class AlunoDAO {
	
private Connection conn;
	
	public boolean inserir(Aluno a) {
		conn = Conexao.getConexao();

		boolean resp = false;
		PreparedStatement pstmt = null;
		
		try {
			pstmt = conn.prepareStatement("INSERT INTO \"aluno\"("
					+ "\"nome\", \"sobrenome\", "
					+ "\"sexo\", \"telefone\", \"data_nascimento\", "
					+ "\"email\", \"matricula\", \"bolsa\")"
					+ " values(?,?,?,?,?,?,?,?)");
			pstmt.setString(1, a.getNome());
			pstmt.setString(2, a.getSobrenome());
			pstmt.setString(3, a.getSexo());
			pstmt.setString(4, a.getTelefone());
			pstmt.setDate(5, a.getDataNascimentoToSQL());
			pstmt.setString(6, a.getEmail());
			pstmt.setInt(7, a.getMatricula());
			pstmt.setBoolean(8, a.getBolsa());
			
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

	public boolean atualizar(Aluno p) {
		conn = Conexao.getConexao();
		
		boolean resp = false;
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement("UPDATE \"aluno\" SET "
					+ "\"nome\" = ? "
					+ ", \"sobrenome\" = ? "
					+ ", \"sexo\" = ? "
					+ ", \"telefone\" = ? "
					+ ", \"data_nascimento\" = ? "
					+ ", \"email\" = ? "
					+ ", \"matricula\" = ? "
					+ ", \"bolsa\" = ? "
					+ "WHERE \"id\" = ?");
			pstmt.setString(1, p.getNome());
			pstmt.setString(2, p.getSobrenome());
			pstmt.setString(3, p.getSexo());
			pstmt.setString(4, p.getTelefone());
			pstmt.setDate(5, p.getDataNascimentoToSQL());
			pstmt.setString(6, p.getEmail());
			pstmt.setInt(7, p.getMatricula());
			pstmt.setBoolean(8, p.getBolsa());
			pstmt.setInt(9, p.getId());
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

	public boolean apagar(Aluno d) {
		conn = Conexao.getConexao();
		
		boolean resp = false;
		PreparedStatement pstmt = null;
		
		try {
			pstmt = conn.prepareStatement("DELETE FROM \"aluno\" WHERE \"id\" = ?");
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

	public ArrayList<Aluno> listar() {
		conn = Conexao.getConexao();
		
		ArrayList<Aluno> alunos = new ArrayList<>();
		Statement stmt = null;
		
		try {
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT \"id\", \"nome\", \"sobrenome\", \"email\", "
					+ "\"sexo\", \"telefone\", \"data_nascimento\", "
					+ "\"matricula\", \"bolsa\" FROM \"aluno\"");
			
			while (rs.next()) {
				alunos.add(new Aluno(rs.getInt("id"), 
						rs.getString("nome"),
						rs.getString("sobrenome"),
						rs.getString("sexo"),
						rs.getString("telefone"),
						rs.getDate("data_nascimento"),
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
				if (stmt != null) {
					stmt.close();
				}
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return alunos;
	}
	
	public Aluno getById(int id) {
		conn = Conexao.getConexao();
		
		Aluno aluno = null;
		PreparedStatement pstmt =  null;
		
		try {
			pstmt = conn.prepareStatement("SELECT "
					+ "\"id\" "
					+ ", \"nome\" "
					+ ", \"sobrenome\" "
					+ ", \"sexo\" "
					+ ", \"telefone\" "
					+ ", \"data_nascimento\" "
					+ ", \"email\" "
					+ ", \"matricula\" "
					+ ", \"bolsa\" "
					+ " FROM \"aluno\" WHERE \"id\" = ?");
			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();
			
			rs.next();
			aluno = new Aluno(rs.getInt("id"), 
					rs.getString("nome"),
					rs.getString("sobrenome"),
					rs.getString("sexo"),
					rs.getString("telefone"),
					rs.getDate("data_nascimento"),
					rs.getString("email"),
					rs.getInt("matricula"),
					rs.getBoolean("bolsa"));
			
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
		return aluno;
	}
	
	public ArrayList<Professor> listarProfessoresByAluno(int id) {
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
}


