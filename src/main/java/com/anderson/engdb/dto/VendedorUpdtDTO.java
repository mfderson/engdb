package com.anderson.engdb.dto;

import com.anderson.engdb.domain.Vendedor;
import com.anderson.engdb.services.validation.VendedorUpdate;

@VendedorUpdate
public class VendedorUpdtDTO extends PessoaUpdtDTO {
	private static final long serialVersionUID = 1L;

	public VendedorUpdtDTO() { }
	
	public VendedorUpdtDTO(Vendedor obj) {
		super(obj);
	}
}
