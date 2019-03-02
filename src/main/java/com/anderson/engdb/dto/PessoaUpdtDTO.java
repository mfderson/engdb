package com.anderson.engdb.dto;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import com.anderson.engdb.domain.Pessoa;

public class PessoaUpdtDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer id;
	
	@NotEmpty(message = "Preenchimento obrigatório")
	@Length(min = 10, max = 50, message = "O tamanho deve ser entre 10 e 50 caracteres")
	private String nome;
	
	public PessoaUpdtDTO() { }

	public PessoaUpdtDTO(Pessoa obj) {
		id = obj.getId();
		nome = obj.getNome();
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
