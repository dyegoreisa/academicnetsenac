package br.com.senac.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import br.com.senac.model.Aluno;
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
		
		ArrayList<Aluno> aluno = new ArrayList<>();
		Statement stmt = null;
		
		try {
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT \"id\", \"nome\", \"sobrenome\", \"email\", "
					+ "\"sexo\", \"telefone\", \"data_nascimento\", "
					+ "\"matricula\", \"bolsa\" FROM \"aluno\"");
			
			while (rs.next()) {
				aluno.add(new Aluno(rs.getInt("id"), 
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
		return aluno;
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
	
	public Aluno getAlunoComTurmas(Aluno p) {
		conn = Conexao.getConexao();
		
		PreparedStatement pstmt = null;
		
		try {
			pstmt = conn.prepareStatement("SELECT t.id, t.nome FROM aluno p "
					+ "LEFT JOIN turma_aluno tp ON tp.id_aluno = p.id "
					+ "LEFT JOIN turma t ON t.id = tp.id_turma "
					+ "WHERE p.id = ? ");
			pstmt.setInt(1, p.getId());
			ResultSet rs = pstmt.executeQuery();
			
			while (rs.next()) {
				p.addTurma(new Turma(rs.getInt("t.id"), rs.getString("t.nome")));
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
		return p;
	}
	
}


