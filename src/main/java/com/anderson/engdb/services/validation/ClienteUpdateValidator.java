package com.anderson.engdb.services.validation;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerMapping;

import com.anderson.engdb.domain.Cliente;
import com.anderson.engdb.domain.enums.Sexo;
import com.anderson.engdb.dto.ClienteUpdtDTO;
import com.anderson.engdb.repositories.ClienteRepository;
import com.anderson.engdb.resources.exceptions.FieldMessage;
import com.anderson.engdb.services.utils.PadronizarNome;

public class ClienteUpdateValidator implements ConstraintValidator<ClienteUpdate, ClienteUpdtDTO> {

	@Autowired
	private ClienteRepository repo;
	
	@Autowired
	private HttpServletRequest request;
	
	@Override
	public void initialize(ClienteUpdate ann) { }
	
	@Override
	public boolean isValid(ClienteUpdtDTO objDto, ConstraintValidatorContext context) {
		
		@SuppressWarnings("unchecked")
		Map<String, String> map = (Map<String, String>) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
		Integer uriId = Integer.parseInt(map.get("id"));
		
		List<FieldMessage> list = new ArrayList<>();
		
		Optional<Cliente> obj = repo.findByNomeIgnoreCase(PadronizarNome.removeWhiteSpacesTogether(objDto.getNome()));
		
		if (obj.isPresent() && !obj.get().getId().equals(uriId))
			list.add(new FieldMessage("nome", "Já existe um cliente com o mesmo nome"));
		
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
