package com.anderson.engdb.dto;

import java.io.Serializable;

import com.anderson.engdb.domain.Pessoa;

public abstract class PessoaViewDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer id;
	private String nome;
	private String cpf;
	
	public PessoaViewDTO() { }

	public PessoaViewDTO(Pessoa obj) {
		id = obj.getId();
		nome = obj.getNome();
		cpf = obj.getCpf();
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
}
