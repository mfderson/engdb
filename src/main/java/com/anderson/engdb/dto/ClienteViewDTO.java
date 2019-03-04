package com.anderson.engdb.dto;

import com.anderson.engdb.domain.Cliente;
import com.anderson.engdb.domain.enums.Sexo;

public class ClienteViewDTO extends PessoaViewDTO {
	private static final long serialVersionUID = 1L;

	private Integer sexo;
	private String nomeVendedor;
	
	public ClienteViewDTO(Cliente obj) {
		super(obj);
		sexo = (obj.getSexo() == null) ? null : obj.getSexo().getCod();
		nomeVendedor = obj.getVendedor().getNome();
	}

	public Sexo getSexo() {
		return Sexo.toEnum(sexo);
	}

	public void setSexo(Integer sexo) {
		this.sexo = sexo;
	}

	public String getNomeVendedor() {
		return nomeVendedor;
	}

	public void setNomeVendedor(String nomeVendedor) {
		this.nomeVendedor = nomeVendedor;
	}
}
