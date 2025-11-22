package br.com.clinicaveterinaria.model;

import java.time.LocalDate;

public class Animal {
	private int idAnimal;
	private String nome;
	private String especie;
	private String raca;
	private LocalDate dataNasc;
	private double peso;
	private int proprietarioId;
	
	public Animal() {}

	public Animal(String nome, String especie, String raca, LocalDate dataNasc, double peso, int proprietarioId) {
		this.nome = nome;
		this.especie = especie;
		this.raca = raca;
		this.dataNasc = dataNasc;
		this.peso = peso;
		this.proprietarioId = proprietarioId;
	}

	public int getIdAnimal() {
		return idAnimal;
	}

	public void setIdAnimal(int idAnimal) {
		this.idAnimal = idAnimal;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEspecie() {
		return especie;
	}

	public void setEspecie(String especie) {
		this.especie = especie;
	}

	public String getRaca() {
		return raca;
	}

	public void setRaca(String raca) {
		this.raca = raca;
	}

	public LocalDate getDataNasc() {
		return dataNasc;
	}

	public void setDataNasc(LocalDate dataNasc) {
		this.dataNasc = dataNasc;
	}

	public double getPeso() {
		return peso;
	}

	public void setPeso(double peso) {
		this.peso = peso;
	}

	public int getProprietarioId() {
		return proprietarioId;
	}
	
	public void setProprietarioId(int proprietarioId) {
		this.proprietarioId = proprietarioId;
	}
	
	@Override
	public String toString() {	
		return "Animal: id = "+idAnimal+" | nome = "+nome+" | especie = "+especie+" | Raça = "+raca+" | Data_Nasc = "+dataNasc+" | Peso = "+peso+" | dono_id = "+proprietarioId;
	}

}
