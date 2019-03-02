package com.anderson.engdb.dto;

import javax.validation.constraints.NotNull;

import com.anderson.engdb.services.validation.ClienteInsert;

@ClienteInsert
public class ClienteNewDTO extends PessoaNewDTO {
	private static final long serialVersionUID = 1L;
	
	@NotNull(message = "Preenchimento obrigatório")
	private Integer sexo;
	
	@NotNull(message = "Preenchimento obrigatório")
	private Integer vendedorId;
	
	public ClienteNewDTO() {
		super();
	}

	public Integer getSexo() {
		return sexo;
	}

	public void setSexo(Integer sexo) {
		this.sexo = sexo;
	}

	public Integer getVendedorId() {
		return vendedorId;
	}

	public void setVendedorId(Integer vendedorId) {
		this.vendedorId = vendedorId;
	}
}
