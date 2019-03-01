package com.anderson.engdb.dto;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.br.CPF;

public abstract class PessoaNewDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@NotEmpty(message = "Preenchimento obrigatório")
	@Length(min = 10, max = 50, message = "O tamanho deve ser entre 10 e 50 caracteres")
	private String nome;
	
	@NotEmpty(message = "Preenchimento obrigatório")
	@CPF
	private String cpf;
	
	public PessoaNewDTO() { }

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
