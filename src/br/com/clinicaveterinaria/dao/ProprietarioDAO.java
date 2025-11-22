package br.com.clinicaveterinaria.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.clinicaveterinaria.model.Proprietario;
import br.com.clinicaveterinaria.util.ConexaoDB;

public class ProprietarioDAO {
	
	public void salvar(Proprietario proprietario) {
		
		String sql = "INSERT INTO proprietarios (cpf, nome, telefone, endereco, email) VALUES (?, ?, ?, ?, ?)";
		
		try (Connection conexao = ConexaoDB.getConexao();
			PreparedStatement stmt = conexao.prepareStatement(sql)){
				
			stmt.setString(1, proprietario.getCpf());
			stmt.setString(2, proprietario.getNome());
			stmt.setString(3, proprietario.getTelefone());
			stmt.setString(4, proprietario.getEndereco());
			stmt.setString(5, proprietario.getEmail());
			
			stmt.executeUpdate();
			
			System.out.println("Proprietario salvo com sucesso!");
		} catch (SQLException e) {
			//verifica unico
			if ("23505".equals(e.getSQLState())) {
				System.err.println("ERRO: Violação de campo único!");
			}else{
				System.err.println("Erro ao salvar proprietário:"+ e.getMessage());
				e.printStackTrace();
			}
			
		}
	}
	
	public List<Proprietario> listarTodos(){
		String sql = "SELECT * FROM proprietarios";
		List<Proprietario> proprietarios = new ArrayList<>();
		
		try (Connection conexao = ConexaoDB.getConexao();
				PreparedStatement stmt = conexao.prepareStatement(sql);
				ResultSet rs = stmt.executeQuery()){
			
			while(rs.next()) {
				Proprietario proprietario = new Proprietario();
				
				proprietario.setIdProprietario(rs.getInt("id_proprietario"));
				proprietario.setCpf(rs.getString("cpf"));
				proprietario.setNome(rs.getString("nome"));
				proprietario.setTelefone(rs.getString("telefone"));
				proprietario.setEndereco(rs.getString("endereco"));
				proprietario.setEmail(rs.getString("email"));
				
				proprietarios.add(proprietario);
			}
		} catch (SQLException e) {
			System.err.println("Erro ao listar proprietarios: "+ e.getMessage());
			e.printStackTrace();
		}
		
		return proprietarios;
	}
	
	public void atualizar(Proprietario proprietario) {
		String sql = "UPDATE proprietarios SET nome = ?, telefone = ?, endereco = ?, email = ? WHERE cpf = ?";
		
		try (Connection conexao = ConexaoDB.getConexao();
				PreparedStatement stmt = conexao.prepareStatement(sql)) {
			
			stmt.setString(1, proprietario.getNome());
			stmt.setString(2, proprietario.getTelefone());
			stmt.setString(3, proprietario.getEndereco());
			stmt.setString(4, proprietario.getEmail());
			stmt.setString(5, proprietario.getCpf());
			
			int linhasAfetadas = stmt.executeUpdate();
			
			if (linhasAfetadas > 0) {
				System.out.println("Proprietário atualizado com sucesso.");
			}else {
				System.err.println("Nenhum proprietário encontrado com o CPF: "+proprietario.getCpf());
			}
			
		} catch (SQLException e) {
			System.err.println("Erro ao atualizar proprietário: "+e.getMessage());
			e.printStackTrace();
		}
	}
	
	public void deletar(int id) {
		String sql = "DELETE FROM proprietarios WHERE id_proprietario = ?";
		
		try (Connection conexao = ConexaoDB.getConexao();
				PreparedStatement stmt = conexao.prepareStatement(sql)) {
			
			stmt.setInt(1, id);
			
			int linhasAfetadas = stmt.executeUpdate();
			
			if (linhasAfetadas > 0) {
				System.out.println("Proprietario excluído com sucesso!");
			}else {
				System.err.println("Nenhum proprietário encontrado com ID: "+id);
			}
			
		} catch (SQLException e) {
			if ("23503".equals(e.getSQLState())) {
				System.err.println("Erro: Não é possível excluir o proprietário pois ele possui animais cadastrados");
			}else {
				System.err.println("Erro ao excluir proprietario: "+ e.getMessage());
				e.printStackTrace();
			}
		}
	}
	
}
