package br.com.senac.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

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
			ResultSet rs = stmt.executeQuery("SELECT \"id\", \"nome\", FROM \"Turma\"");
			
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
			pstmt = conn.prepareStatement("SELECT \"id\", \"nome\" FROM \"Turma\" WHERE \"id\" = ?");
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
	
}
