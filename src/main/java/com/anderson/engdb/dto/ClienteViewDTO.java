package com.anderson.engdb.dto;

import com.anderson.engdb.domain.Cliente;

public class ClienteViewDTO extends PessoaViewDTO {
	private static final long serialVersionUID = 1L;

	private String sexo;
	private String nomeVendedor;
	
	public ClienteViewDTO(Cliente obj) {
		super(obj);
		sexo = obj.getSexo().getDescricao();
		nomeVendedor = obj.getVendedor().getNome();
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public String getNomeVendedor() {
		return nomeVendedor;
	}

	public void setNomeVendedor(String nomeVendedor) {
		this.nomeVendedor = nomeVendedor;
	}
}
