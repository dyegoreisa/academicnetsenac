package br.com.senac.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import br.com.senac.model.Professor;

public class ProfessorDAO {

	private Connection conn;
	
	public boolean inserir(Professor d) {
		conn = Conexao.getConexao();

		boolean resp = false;

		try {
			PreparedStatement pstmt = conn.prepareStatement("INSERT INTO \"TABProfessor\"(\"Nome\") values(?)");
			pstmt.setString(1, d.getNome());
			resp = pstmt.execute();
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return resp;
	}

	public boolean atualizar(Professor d) {
		conn = Conexao.getConexao();
		
		boolean resp = false;
		
		try {
			PreparedStatement pstmt = conn.prepareStatement("UPDATE \"TABProfessor\" SET \"Nome\" = ? WHERE \"Codigo\" = ?");
			pstmt.setString(1, d.getNome());
			pstmt.setInt(2, d.getId());
			resp = pstmt.execute();
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return resp;
	}

	public boolean apagar(Professor d) {
		conn = Conexao.getConexao();
		
		boolean resp = false;
		
		try {
			PreparedStatement pstmt = conn.prepareStatement("DELETE FROM \"TABProfessor\" WHERE \"Codigo\" = ?");
			pstmt.setInt(1, d.getId());
			resp = pstmt.execute();
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return resp;
	}

	public ArrayList<Professor> listar() {
		conn = Conexao.getConexao();
		
		ArrayList<Professor> professores = new ArrayList<>();
		
		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT \"id\", \"nome\" FROM \"professor\"");
			
			while (rs.next()) {
				professores.add(new Professor(rs.getInt("Codigo"), rs.getString("Nome")));
			}
			
			rs.close();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return professores;
	}
	
	public Professor getById(int id) {
		conn = Conexao.getConexao();
		
		Professor professor = null;
		
		try {
			PreparedStatement pstmt = conn.prepareStatement("SELECT \"Codigo\", \"Nome\" FROM \"professor\" WHERE \"Codigo\" = ?");
			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();
			
			rs.next();
			professor = new Professor(rs.getInt("Codigo"), rs.getString("Nome"));
			
			
			rs.close();
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return professor;
	}
	
}
