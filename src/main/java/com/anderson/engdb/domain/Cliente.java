package com.anderson.engdb.domain;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.anderson.engdb.domain.enums.Sexo;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Cliente extends Pessoa {
	private static final long serialVersionUID = 1L;

	private Integer sexo;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "vendedor_id")
	private Vendedor vendedor;
	
	public Cliente() { }

	public Cliente(Integer id, String nome, String cpf, Sexo sexo, Vendedor vendedor) {
		super(id, nome, cpf);
		this.sexo = (sexo == null) ? null : sexo.getCod();
		this.vendedor = vendedor;
	}

	public Sexo getSexo() {
		return Sexo.toEnum(sexo);
	}

	public void setSexo(Sexo sexo) {
		this.sexo = sexo.getCod();
	}

	public Vendedor getVendedor() {
		return vendedor;
	}

	public void setVendedor(Vendedor vendedor) {
		this.vendedor = vendedor;
	}
}
