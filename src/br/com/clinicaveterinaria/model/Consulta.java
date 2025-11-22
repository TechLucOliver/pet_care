package br.com.clinicaveterinaria.model;

import java.time.LocalDateTime;

public class Consulta {
	private int idConsulta;
	private LocalDateTime dataHora;
	private String diagnostico;
	private double valor;
	
	//Foreign keys
	private int animalId;
	private int veterinarioId;
	
	public Consulta() {}

	public Consulta(LocalDateTime dataHora, String diagnostico, double valor, int animalId, int veterinarioId) {
		this.dataHora = dataHora;
		this.diagnostico = diagnostico;
		this.valor = valor;
		this.animalId = animalId;
		this.veterinarioId = veterinarioId;
	}

	public int getIdConsulta() {
		return idConsulta;
	}

	public void setIdConsulta(int idConsulta) {
		this.idConsulta = idConsulta;
	}

	public LocalDateTime getDataHora() {
		return dataHora;
	}

	public void setDataHora(LocalDateTime dataHora) {
		this.dataHora = dataHora;
	}

	public String getDiagnostico() {
		return diagnostico;
	}

	public void setDiagnostico(String diagnostico) {
		this.diagnostico = diagnostico;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	public int getAnimalId() {
		return animalId;
	}

	public void setAnimalId(int animalId) {
		this.animalId = animalId;
	}

	public int getVeterinarioId() {
		return veterinarioId;
	}
	
	public void setVeterinarioId(int veterinarioId) {
		this.veterinarioId = veterinarioId;
	}
	
	@Override
	public String toString() {
		return "Consulta: id = "+idConsulta+" | data_hora = "+dataHora+" | animal_id = "+animalId+" | veterinario_id = "+veterinarioId+" | valor = "+valor+" | Diagnostico = "+diagnostico;
	}
}
