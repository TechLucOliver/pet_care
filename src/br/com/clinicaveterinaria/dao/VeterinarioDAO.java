package br.com.clinicaveterinaria.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.clinicaveterinaria.model.Veterinario;
import br.com.clinicaveterinaria.util.ConexaoDB;

public class VeterinarioDAO {
	
	public void salvar(Veterinario veterinario) {
		String sql = "INSERT INTO veterinarios (crmv, nome, especialidade, telefone) VALUES (?, ?, ?, ?)";
		
		try (Connection conexao = ConexaoDB.getConexao();
				PreparedStatement stmt = conexao.prepareStatement(sql)){
			
			stmt.setString(1, veterinario.getCrmv());
			stmt.setString(2, veterinario.getNome());
			stmt.setString(3, veterinario.getEspecialidade());
			stmt.setString(4, veterinario.getTelefone());
			
			stmt.executeUpdate();
			
			System.out.println("Veterinário salvo com sucesso");
			
		} catch (SQLException e) {
			if ("23505".equals(e.getSQLState())) {
				System.err.println("ERRO: Já existe um veterinário cadastrado com o CRMV '" + veterinario.getCrmv() + "'.");
			}else {
				System.err.println("Erro ao salvar veterinario: " + e.getMessage());
				e.printStackTrace();
			}
			
		}
	}
	
	public List<Veterinario> listarTodos(){
		String sql = "SELECT * FROM veterinarios";
		
		List<Veterinario> veterinarios = new ArrayList<>();
		
		try (Connection conexao = ConexaoDB.getConexao();
				PreparedStatement stmt = conexao.prepareStatement(sql);
				ResultSet rs = stmt.executeQuery()){
			
			while(rs.next()) {
				Veterinario vet = new Veterinario();
				
				vet.setIdVeterinario(rs.getInt("id_veterinario"));
				vet.setCrmv(rs.getString("crmv"));
				vet.setNome(rs.getString("nome"));
				vet.setEspecialidade(rs.getString("especialidade"));
                vet.setTelefone(rs.getString("telefone"));
                
                veterinarios.add(vet);
			}
			
		} catch (SQLException e) {
			System.err.println("Erro ao listar veterinários: "+ e.getMessage());
			e.printStackTrace();
		}
		
		return veterinarios;
	}
	
	
	public void atualizar (Veterinario veterinario) {
		String sql = "UPDATE veterinarios SET nome = ?, especialidade = ?, telefone = ? WHERE crmv = ?";
		
		try (Connection conexao = ConexaoDB.getConexao();
				PreparedStatement stmt = conexao.prepareStatement(sql)){
			
			stmt.setString(1, veterinario.getNome());
			stmt.setString(2, veterinario.getEspecialidade());
			stmt.setString(3, veterinario.getTelefone());
			stmt.setString(4, veterinario.getCrmv());
			
			int linhasAfetadas = stmt.executeUpdate();
			
			if (linhasAfetadas > 0) {
				System.out.println("Veterinário atualizado com sucesso!");
			}else {
				System.err.println("Nenhum veterinário encontrado com o CRMV: "+veterinario.getCrmv());
			}
			
		} catch (SQLException e) {
			System.err.println("Erro ao atualizar veterinários: "+ e.getMessage());
			e.printStackTrace();
		}
	}
	
	
	public void deletar(int id) {
		String sql = "DELETE FROM veterinarios WHERE id_veterinario = ?";
		
		try (Connection conexao = ConexaoDB.getConexao();
				PreparedStatement stmt = conexao.prepareStatement(sql)){
			
			stmt.setInt(1, id);
			
			int linhasAfetadas = stmt.executeUpdate();
			
			if (linhasAfetadas > 0) {
                System.out.println("Veterinário excluído com sucesso!");
            } else {
                System.err.println("Nenhum veterinário encontrado com ID: "+id);
            }

        } catch (SQLException e) {
            if ("23503".equals(e.getSQLState())) { 
                System.err.println("Erro: Não é possível excluir o veterinário pois ele possui consultas cadastradas.");
            } else {
                System.err.println("Erro ao excluir veterinário: " + e.getMessage());
                e.printStackTrace();
            }
        }
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
