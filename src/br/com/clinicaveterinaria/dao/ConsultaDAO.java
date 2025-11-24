package br.com.clinicaveterinaria.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.postgresql.util.PSQLException;

import br.com.clinicaveterinaria.model.Consulta;
import br.com.clinicaveterinaria.util.ConexaoDB;

public class ConsultaDAO {
	public void salvar(Consulta consulta) {
		String sql = "INSERT INTO consultas (data_hora, diagnostico, valor, animal_id, veterinario_id) "
				+ "VALUES (?, ?, ?, ?, ?)";

		try (Connection conexao = ConexaoDB.getConexao(); PreparedStatement stmt = conexao.prepareStatement(sql)) {

			stmt.setObject(1, consulta.getDataHora());
			stmt.setString(2, consulta.getDiagnostico());
			stmt.setDouble(3, consulta.getValor());

			// Foreign keys
			stmt.setInt(4, consulta.getAnimalId());
			stmt.setInt(5, consulta.getVeterinarioId());

			stmt.executeUpdate();

			try (ResultSet rs = stmt.getGeneratedKeys()) {
				if (rs.next()) {
					consulta.setIdConsulta(rs.getInt(1));
				}
			}

			System.out.println("Consulta salva com sucesso!");

		} catch (PSQLException e) {
			// verifica campo inválido
			System.err.println("Erro ao criar");
			System.err.println(e.getServerErrorMessage());

		} catch (SQLException e) {
			if ("23503".equals(e.getSQLState())) {
				System.err.println("Erro, o animal (id: " + consulta.getAnimalId() + ") ou o veterinario (crmv: "
						+ consulta.getVeterinarioId() + ") não existem.");
			} else {
				System.err.println("Erro ao salvar consulta: " + e.getMessage());
				e.printStackTrace();
			}
		}
	}

	public List<Consulta> listarTodos() {
		String sql = "SELECT * FROM consultas";
		List<Consulta> consultas = new ArrayList<>();

		try (Connection conexao = ConexaoDB.getConexao();
				PreparedStatement stmt = conexao.prepareStatement(sql);
				ResultSet rs = stmt.executeQuery()) {

			while (rs.next()) {
				Consulta consulta = new Consulta();

				consulta.setIdConsulta(rs.getInt("id_consulta"));
				consulta.setDataHora(rs.getObject("data_hora", LocalDateTime.class));
				consulta.setDiagnostico(rs.getString("diagnostico"));
				consulta.setValor(rs.getDouble("valor"));
				consulta.setAnimalId(rs.getInt("animal_id"));
				consulta.setVeterinarioId(rs.getInt("veterinario_id"));

				consultas.add(consulta);
			}
		} catch (SQLException e) {
			System.err.println("Erro ao listar " + e.getMessage());
			e.printStackTrace();
		}

		return consultas;
	}

	public void atualizar(Consulta consulta) {
		String sql = "UPDATE consultas SET data_hora = ?, diagnostico = ?, valor = ?, animal_id = ?, veterinario_id = ? "
				+ "WHERE id_consulta = ?";

		try (Connection conexao = ConexaoDB.getConexao(); PreparedStatement stmt = conexao.prepareStatement(sql)) {

			stmt.setObject(1, consulta.getDataHora());
			stmt.setString(2, consulta.getDiagnostico());
			stmt.setDouble(3, consulta.getValor());
			stmt.setInt(4, consulta.getAnimalId());
			stmt.setInt(5, consulta.getVeterinarioId());

			stmt.setInt(6, consulta.getIdConsulta());

			int linhasAfetadas = stmt.executeUpdate();

			if (linhasAfetadas > 0) {
				System.out.println("Consulta atualizada!");
			} else {
				System.err.println("Nenhuma consulta foi encontrada com o ID: " + consulta.getIdConsulta());
			}

		} catch (PSQLException e) {
			// verifica campo inválido
			System.err.println("Erro ao atualizar");
			System.err.println(e.getServerErrorMessage());

		} catch (SQLException e) {
			if ("23503".equals(e.getSQLState())) {
				System.err.println("Erro ao atualizar, o animal ou o veterinario informado não existe");
			} else {
				System.err.println("Erro ao atualizar consulta " + e.getMessage());
				e.printStackTrace();
			}
		}
	}

	public void deletar(int idConsulta) {
		String sql = "DELETE FROM consultas WHERE id_consulta = ?";

		try (Connection conexao = ConexaoDB.getConexao(); PreparedStatement stmt = conexao.prepareStatement(sql)) {

			stmt.setInt(1, idConsulta);

			int linhasAfetadas = stmt.executeUpdate();

			if (linhasAfetadas > 0) {
				System.out.println("Consulta exluída com sucesso!");
			} else {
				System.out.println("Nenhuma consulta encontrada!");
			}

		} catch (SQLException e) {
			System.err.println("Erro ao excluir consulta " + e.getMessage());
			e.printStackTrace();
		}
	}

}
