package com.francisco.os.dtos;

import java.io.Serializable;

import org.hibernate.validator.constraints.br.CPF;

import com.francisco.os.domain.Tecnico;

import jakarta.validation.constraints.NotEmpty;

public class TecnicoDTO  implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer id;
	
	@NotEmpty(message = "O campo nome é requerido")
	private String nome;
	
	@NotEmpty(message = "O campo cpf é requerido")
	@CPF
	private String cpf;
	
	@NotEmpty(message = "O campo telefone é requerido")
	private String telefone;
	
	public TecnicoDTO() {
		super();
	}
	public TecnicoDTO(Tecnico obj) {
		super();
		this.id = obj.getId();
		this.nome = obj.getNome();
		this.cpf = obj.getCpf();
		this.telefone = obj.getTelefone();
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	
	
	
	
	
	
}
