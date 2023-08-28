package com.francisco.os.dtos;

import java.io.Serializable;

import com.francisco.os.domain.Servico;

import jakarta.validation.constraints.NotEmpty;

public class ServicoDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer id;

	@NotEmpty(message = "O campo nome é requerido")
	private String nome;

	public ServicoDTO() {
		super();
	}

	public ServicoDTO(Servico obj) {
		super();
		this.id = obj.getId();
		this.nome = obj.getNome();

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

}
