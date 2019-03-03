package com.anderson.engdb.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

@Entity
public class Vendedor extends Pessoa {
	private static final long serialVersionUID = 1L;
	
	@OneToMany(mappedBy = "vendedor", cascade = CascadeType.ALL)
	private List<Cliente> clientes = new ArrayList<>();
	
	public Vendedor() { }
	
	public Vendedor(Integer id, String nome, String cpf) {
		super(id, nome, cpf);
	}

	public List<Cliente> getClientes() {
		return clientes;
	}

	public void setClientes(List<Cliente> clientes) {
		this.clientes = clientes;
	}
}
