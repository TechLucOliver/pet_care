package br.com.clinicaveterinaria.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.postgresql.util.PSQLException;

import br.com.clinicaveterinaria.dao.AnimalDAO;
import br.com.clinicaveterinaria.dao.ConsultaDAO;
import br.com.clinicaveterinaria.dao.ProprietarioDAO;
import br.com.clinicaveterinaria.dao.VeterinarioDAO;
import br.com.clinicaveterinaria.model.Animal;
import br.com.clinicaveterinaria.model.Consulta;
import br.com.clinicaveterinaria.model.Proprietario;
import br.com.clinicaveterinaria.model.Veterinario;

public class ClinicaController {
	private ProprietarioDAO proprietarioDAO;
	private VeterinarioDAO veterinarioDAO;
	private AnimalDAO animalDAO;
	private ConsultaDAO consultaDAO;

	public ClinicaController() {
		this.proprietarioDAO = new ProprietarioDAO();
		this.veterinarioDAO = new VeterinarioDAO();
		this.animalDAO = new AnimalDAO();
		this.consultaDAO = new ConsultaDAO();
	}

	// METODOS PROPRIETÁRIOS////////////////////////////////////
	public void salvarProprietario(String cpf, String nome, String telefone, String endereco, String email)
			throws PSQLException {
		Proprietario proprietario = new Proprietario(cpf, nome, telefone, endereco, email);
		proprietarioDAO.salvar(proprietario);
	}

	public List<Proprietario> listarProprietarios() {
		return proprietarioDAO.listarTodos();
	}

	public void atualizarProprietario(String cpf, String nome, String telefone, String endereco, String email) {
		Proprietario proprietario = new Proprietario(cpf, nome, telefone, endereco, email);
		proprietarioDAO.atualizar(proprietario);
	}

	public void deletarProprietario(int id) {
		proprietarioDAO.deletar(id);
	}
	/////////////////////////////////////////////////////////

	// METODOS VETERINÁRIOS////////////////////////////////////
	public void salvarVeterinario(String crmv, String nome, String especialidade, String telefone) {
		Veterinario veterinario = new Veterinario(crmv, nome, especialidade, telefone);
		veterinarioDAO.salvar(veterinario);
	}

	public List<Veterinario> listarVeterinarios() {
		return veterinarioDAO.listarTodos();
	}

	public void atualizarVeterinario(String crmv, String nome, String especialidade, String telefone) {
		Veterinario veterinario = new Veterinario(crmv, nome, especialidade, telefone);
		veterinarioDAO.atualizar(veterinario);
	}

	public void deletarVeterinario(int id) {
		veterinarioDAO.deletar(id);
	}
	/////////////////////////////////////////////////////////

	// METODOS ANIMAIS////////////////////////////////////
	public void salvarAnimal(String nome, String especie, String raca, LocalDate dataNasc, double peso,
			int proprietario_id) {
		Animal animal = new Animal(nome, especie, raca, dataNasc, peso, proprietario_id);
		animalDAO.salvar(animal);
	}

	public List<Animal> listarAnimais() {
		return animalDAO.listarTodos();
	}

	public void atualizarAnimal(int idAnimal, String nome, String especie, String raca, LocalDate dataNasc, double peso,
			int proprietario_id) {
		Animal animal = new Animal(nome, especie, raca, dataNasc, peso, proprietario_id);
		animal.setIdAnimal(idAnimal);
		animalDAO.atualizar(animal);
	}

	public void deletarAnimal(int idAnimal) {
		animalDAO.deletar(idAnimal);
	}
	/////////////////////////////////////////////////////////

	// METODOS CONSULTAS////////////////////////////////////
	public void salvarConsulta(LocalDateTime dataHora, String diagnostico, double valor, int animalId,
			int veterinario_id) {
		Consulta consulta = new Consulta(dataHora, diagnostico, valor, animalId, veterinario_id);
		consultaDAO.salvar(consulta);
	}

	public List<Consulta> listarConsultas() {
		return consultaDAO.listarTodos();
	}

	public void atualizarConsulta(int idConsulta, LocalDateTime dataHora, String diagnostico, double valor,
			int animalId, int veterinario_id) {
		Consulta consulta = new Consulta(dataHora, diagnostico, valor, animalId, veterinario_id);
		consulta.setIdConsulta(idConsulta);
		consultaDAO.atualizar(consulta);
	}

	public void deletarConsulta(int idConsulta) {
		consultaDAO.deletar(idConsulta);
	}

}
