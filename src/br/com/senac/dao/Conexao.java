package br.com.senac.dao;

import java.sql.Connection;
import java.sql.DriverManager;

public class Conexao {
	
	private Conexao() {}
	
	public static Connection getConexao() {
		Connection conexao = null;
		try {
			Class.forName("org.postgresql.Driver");
			conexao = DriverManager
					.getConnection(
							"jdbc:postgresql://localhost:5432/AcademicNet",
							"postgres", "banco123");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conexao;
	}
}
