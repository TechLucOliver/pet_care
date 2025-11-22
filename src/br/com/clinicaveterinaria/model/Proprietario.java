package br.com.clinicaveterinaria.model;

public class Proprietario {
	private int idProprietario;
	private String cpf;
	private String nome;
	private String telefone;
	private String endereco;
	private String email;
	
	public Proprietario() {}

	public Proprietario(String cpf, String nome, String telefone, String endereco, String email) {
		this.cpf = cpf;
		this.nome = nome;
		this.telefone = telefone;
		this.endereco = endereco;
		this.email = email;
	}
	
	public int getIdProprietario() {
		return idProprietario;
	}
	
	public void setIdProprietario(int idProprietario) {
		this.idProprietario = idProprietario;
	}
	
	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	
	@Override
	public String toString() {
        return "Proprietário: id = "+idProprietario+" | cpf = "+cpf+" | Nome = "+nome+" | telefone = "+telefone+" | "
        		+ "Endereço: "+endereco+" | Email: "+email;
    }
}
