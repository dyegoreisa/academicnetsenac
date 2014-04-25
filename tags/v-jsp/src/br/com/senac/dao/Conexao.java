package br.com.senac.dao;

import java.sql.Connection;
import java.sql.DriverManager;

import org.hibernate.Session;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

public class Conexao {
	
	private Conexao() {}
	
	public static Connection getConexao() {
		Connection conexao = null;
		try {
			Class.forName("org.postgresql.Driver");
			conexao = DriverManager
					.getConnection(
							"jdbc:postgresql://localhost:5432/academicnetsenac",
							"postgres", "banco123");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conexao;
	}
	
	public static Session getSession() {
		Configuration config = new Configuration();
		config.configure();
	
		ServiceRegistry registry = new ServiceRegistryBuilder().applySettings(
				config.getProperties()).buildServiceRegistry();
	
		return config.buildSessionFactory(registry).openSession();
	}
}
