package com.anderson.engdb.services.validation;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.anderson.engdb.domain.enums.Sexo;
import com.anderson.engdb.dto.ClienteNewDTO;
import com.anderson.engdb.repositories.ClienteRepository;
import com.anderson.engdb.resources.exceptions.FieldMessage;

public class ClienteInsertValidator implements ConstraintValidator<ClienteInsert, ClienteNewDTO> {

	@Autowired
	private ClienteRepository repo;
	
	@Override
	public void initialize(ClienteInsert ann) { }
	
	@Override
	public boolean isValid(ClienteNewDTO objDto, ConstraintValidatorContext context) {
		
		List<FieldMessage> list = new ArrayList<>();
		
		if (repo.findByCpf(objDto.getCpf()).isPresent())
			list.add(new FieldMessage("cpf", "Já existe um cliente para este CPF"));
		
		if (repo.findByNomeIgnoreCase(objDto.getNome()).isPresent())
			list.add(new FieldMessage("nome", "Já existe um cliente com mesmo nome"));
		
		if (objDto.getSexo() != Sexo.MASCULINO.getCod() && objDto.getSexo() != Sexo.FEMININO.getCod())
			list.add(new FieldMessage("sexo", "Opção inválida"));
			
		for (FieldMessage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
					.addConstraintViolation();
		}
		return list.isEmpty();
	}
}
