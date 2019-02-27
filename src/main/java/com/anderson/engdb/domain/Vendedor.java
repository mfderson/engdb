package com.anderson.engdb.domain;

import javax.persistence.Entity;

@Entity
public class Vendedor extends Pessoa {
	private static final long serialVersionUID = 1L;
	
	public Vendedor() { }
	
	public Vendedor(Integer id, String nome, String cpf) {
		super(id, nome, cpf);
	}
}
