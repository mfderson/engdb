package com.anderson.engdb.dto;

import javax.validation.constraints.NotNull;

import com.anderson.engdb.domain.Cliente;
import com.anderson.engdb.services.validation.ClienteUpdate;

@ClienteUpdate
public class ClienteUpdtDTO extends PessoaUpdtDTO {
	private static final long serialVersionUID = 1L;

	private Integer id;
	
	@NotNull(message = "Preenchimento obrigatório")
	private Integer sexo;
	
	public ClienteUpdtDTO() { }
	
	public ClienteUpdtDTO(Cliente obj) {
		super(obj);
		this.sexo = obj.getSexo().getCod();
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getSexo() {
		return sexo;
	}

	public void setSexo(Integer sexo) {
		this.sexo = sexo;
	}
}
