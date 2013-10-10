package br.com.senac.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import br.com.senac.model.Professor;
import br.com.senac.model.Turma;

public class ProfessorDAO {

	private Connection conn;
	
	public boolean inserir(Professor p) {
		conn = Conexao.getConexao();

		boolean resp = false;
		PreparedStatement pstmt = null;
		
		try {
			pstmt = conn.prepareStatement("INSERT INTO \"professor\"("
					+ "\"nome\", \"sobrenome\", \"email\", \"especialidade\", \"salario\", \"vinculo\")"
					+ " values(?,?,?,?,?,?)");
			pstmt.setString(1, p.getNome());
			pstmt.setString(2, p.getSobrenome());
			pstmt.setString(3, p.getEmail());
			pstmt.setString(4, p.getEspecialidade());
			pstmt.setDouble(5, p.getSalario());
			pstmt.setString(6, p.getVinculo());
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

	public boolean atualizar(Professor p) {
		conn = Conexao.getConexao();
		
		boolean resp = false;
		PreparedStatement pstmt = null;
		
		try {
			pstmt = conn.prepareStatement("UPDATE \"professor\" SET "
					+ "\"nome\" = ? "
					+ ", \"sobrenome\" = ? "
					+ ", \"sexo\" = ? "
					+ ", \"telefone\" = ? "
					+ ", \"data_nacimento\" = ? "
					+ ", \"email\" = ? "
					+ ", \"especialidade\" = ? "
					+ ", \"salario\" = ? "
					+ ", \"vindulo\" = ? "
					+ "WHERE \"id\" = ?");
			pstmt.setString(1, p.getNome());
			pstmt.setString(2, p.getSobrenome());
			pstmt.setString(3, p.getSexo());
			pstmt.setString(4, p.getTelefone());
			pstmt.setDate(5, p.getDataNascimentoToSQL());
			pstmt.setString(6, p.getEmail());
			pstmt.setString(7, p.getEspecialidade());
			pstmt.setDouble(8, p.getSalario());
			pstmt.setString(9, p.getVinculo());
			
			pstmt.setInt(10, p.getId());
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

	public boolean apagar(Professor d) {
		conn = Conexao.getConexao();
		
		boolean resp = false;
		PreparedStatement pstmt = null;
		
		try {
			pstmt = conn.prepareStatement("DELETE FROM \"professor\" WHERE \"id\" = ?");
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

	public ArrayList<Professor> listar() {
		conn = Conexao.getConexao();
		
		ArrayList<Professor> professores = new ArrayList<>();
		Statement stmt = null;
		
		try {
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT \"id\", \"nome\", \"sobrenome\", \"email\", "
					+ "\"especialidade\", \"salario\", \"vinculo\" FROM \"professor\"");
			
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
				if (stmt != null) {
					stmt.close();
				}
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
					+ ", \"especialidade\" "
					+ ", \"salario\" "
					+ ", \"vinculo\" "
					+ " FROM \"professor\" WHERE \"id\" = ?");
			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();
			
			rs.next();
			professor = new Professor(rs.getInt("id"), 
					rs.getString("nome"),
					rs.getString("sobrenome"),
					rs.getString("sexo"),
					rs.getString("telefone"),
					rs.getDate("data_nascimento"),
					rs.getString("email"),
					rs.getString("especialidade"),
					rs.getDouble("salario"),
					rs.getString("vinculo"));
			
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
		return professor;
	}
	
	public Professor getProfessorComTurmas(Professor p) {
		conn = Conexao.getConexao();
		
		PreparedStatement pstmt = null;
		
		try {
			pstmt = conn.prepareStatement("SELECT t.id, t.nome FROM professor p "
					+ "LEFT JOIN turma_professor tp ON tp.id_professor = p.id "
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
