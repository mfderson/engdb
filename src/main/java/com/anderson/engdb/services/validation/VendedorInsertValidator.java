package com.anderson.engdb.services.validation;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.anderson.engdb.dto.VendedorNewDTO;
import com.anderson.engdb.repositories.VendedorRepository;
import com.anderson.engdb.resources.exceptions.FieldMessage;

public class VendedorInsertValidator implements ConstraintValidator<VendedorInsert, VendedorNewDTO> {

	@Autowired
	private VendedorRepository repo;
	
	@Override
	public void initialize(VendedorInsert ann) { }
	
	@Override
	public boolean isValid(VendedorNewDTO objDto, ConstraintValidatorContext context) {
		
		List<FieldMessage> list = new ArrayList<>();
		
		if (repo.findByCpf(objDto.getCpf()).isPresent())
			list.add(new FieldMessage("cpf", "Já existe um vendedor para este CPF"));
		
		for (FieldMessage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
					.addConstraintViolation();
		}
		return list.isEmpty();
	}
}
