package br.com.clinicaveterinaria.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.postgresql.util.PSQLException;

import br.com.clinicaveterinaria.model.Animal;
import br.com.clinicaveterinaria.util.ConexaoDB;

public class AnimalDAO {
	public void salvar(Animal animal) {
		String sql = "INSERT INTO animais (nome, especie, raca, data_nasc, peso, proprietario_id) VALUES (?, ?, ?, ?, ?, ?)";

		try (Connection conexao = ConexaoDB.getConexao(); PreparedStatement stmt = conexao.prepareStatement(sql)) {

			stmt.setString(1, animal.getNome());
			stmt.setString(2, animal.getEspecie());
			stmt.setString(3, animal.getRaca());
			stmt.setObject(4, animal.getDataNasc());
			stmt.setDouble(5, animal.getPeso());
			stmt.setInt(6, animal.getProprietarioId());

			stmt.executeUpdate();

			System.out.println("Animal salvo com sucesso!");

		} catch (PSQLException e) {
			// verifica campo inválido
			System.err.println("Erro ao criar animal");
			System.err.println(e.getServerErrorMessage());

		} catch (SQLException e) {
			if ("23503".equals(e.getSQLState())) {
				System.err.println("Erro: O proprietário com ID '" + animal.getProprietarioId() + "' não existe.");
			} else {
				System.err.println("Erro ao salvar animal: " + e.getMessage());
				e.printStackTrace();
			}
		}
	}

	public List<Animal> listarTodos() {
		String sql = "SELECT * FROM animais";

		List<Animal> animais = new ArrayList<>();

		try (Connection conexao = ConexaoDB.getConexao();
				PreparedStatement stmt = conexao.prepareStatement(sql);
				ResultSet rs = stmt.executeQuery()) {

			while (rs.next()) {
				Animal animal = new Animal();

				animal.setIdAnimal(rs.getInt("id_animal"));
				animal.setNome(rs.getString("nome"));
				animal.setEspecie(rs.getString("especie"));
				animal.setRaca(rs.getString("raca"));
				animal.setDataNasc(rs.getDate("data_nasc").toLocalDate());
				animal.setPeso(rs.getDouble("peso"));
				animal.setProprietarioId(rs.getInt("proprietario_id"));

				animais.add(animal);
			}
		} catch (SQLException e) {
			System.err.println("Erro ao listar animais: " + e.getMessage());
			e.printStackTrace();
		}

		return animais;
	}

	public void atualizar(Animal animal) {
		String sql = "UPDATE animais SET nome = ?, especie = ?, raca = ?, data_nasc = ?, peso = ?, proprietario_id = ? WHERE id_animal = ?";

		try (Connection conexao = ConexaoDB.getConexao(); PreparedStatement stmt = conexao.prepareStatement(sql)) {

			stmt.setString(1, animal.getNome());
			stmt.setString(2, animal.getEspecie());
			stmt.setString(3, animal.getRaca());
			stmt.setObject(4, animal.getDataNasc());
			stmt.setDouble(5, animal.getPeso());
			stmt.setInt(6, animal.getProprietarioId());
			stmt.setInt(7, animal.getIdAnimal());

			int linhasAfetadas = stmt.executeUpdate();

			if (linhasAfetadas > 0) {
				System.out.println("Animal atualizado com sucesso!");
			} else {
				System.err.println("Nenhum animal foi encontrado com o ID: " + animal.getIdAnimal());
			}

		} catch (PSQLException e) {
			// verifica campo inválido
			System.err.println("Erro ao atualizar");
			System.err.println(e.getServerErrorMessage());

		} catch (SQLException e) {
			if ("23503".equals(e.getSQLState())) {
				System.err.println(
						"Erro ao atualizar: O proprietário de ID '" + animal.getProprietarioId() + "' não existe.");
			} else {
				System.err.println("Erro ao atualizar animal: " + e.getMessage());
				e.printStackTrace();
			}
		}

	}

	public void deletar(int idAnimal) {
		String sql = "DELETE FROM animais WHERE id_animal = ?";

		try (Connection conexao = ConexaoDB.getConexao(); PreparedStatement stmt = conexao.prepareStatement(sql)) {

			stmt.setInt(1, idAnimal);

			int linhasAfetadas = stmt.executeUpdate();

			if (linhasAfetadas > 0) {
				System.out.println("Animal excluído com sucesso!");
			} else {
				System.err.println("O animal de id: " + idAnimal + " não existe");
			}

		} catch (SQLException e) {
			if ("23503".equals(e.getSQLState())) {
				System.err.println("Erro: Não é possível excluir o animal pois ele possui consultas cadastradas.");
			} else {
				System.err.println("Erro ao excluir animal: " + e.getMessage());
				e.printStackTrace();
			}
		}
	}
}
