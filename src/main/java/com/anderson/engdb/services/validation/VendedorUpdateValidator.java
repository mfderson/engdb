package com.anderson.engdb.services.validation;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.anderson.engdb.dto.VendedorUpdtDTO;
import com.anderson.engdb.repositories.VendedorRepository;
import com.anderson.engdb.resources.exceptions.FieldMessage;

public class VendedorUpdateValidator implements ConstraintValidator<VendedorUpdate, VendedorUpdtDTO> {

	@Autowired
	private VendedorRepository repo;
	
	@Override
	public void initialize(VendedorUpdate ann) { }
	
	@Override
	public boolean isValid(VendedorUpdtDTO objDto, ConstraintValidatorContext context) {
		
		List<FieldMessage> list = new ArrayList<>();
		
		if (repo.findByNomeIgnoreCase(objDto.getNome()).isPresent())
			list.add(new FieldMessage("nome", "Já existe um vendedor com mesmo nome"));
		
		for (FieldMessage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
					.addConstraintViolation();
		}
		return list.isEmpty();
	}
}
