package br.com.clinicaveterinaria.util;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseCleaner {
	public static void limparBanco() {
		String sql = "TRUNCATE TABLE proprietarios, veterinarios RESTART IDENTITY CASCADE";
		
		try (Connection conexao = ConexaoDB.getConexao();
				Statement stmt = conexao.createStatement()){
			
			stmt.executeUpdate(sql);
			System.out.println("Banco de dados limpo");
			
		} catch (SQLException e) {
			System.err.println("Erro ao limpar o banco: "+ e.getMessage());
			e.printStackTrace();
		}
	}
}
