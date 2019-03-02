package com.anderson.engdb.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.anderson.engdb.domain.Cliente;
import com.anderson.engdb.domain.Vendedor;
import com.anderson.engdb.domain.enums.Sexo;
import com.anderson.engdb.dto.ClienteNewDTO;
import com.anderson.engdb.repositories.ClienteRepository;
import com.anderson.engdb.services.exceptions.ObjectNotFoundException;
import com.anderson.engdb.services.utils.PadronizarNome;

@Service
public class ClienteService {
	
	@Autowired
	private ClienteRepository repo;
	
	@Autowired
	private VendedorService vendedorService;

	public Cliente findById(Integer id) {
		
		Optional<Cliente> obj = repo.findById(id);		
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Cliente.class.getName()));
	}
	
	public Cliente findByCpf(String cpf) {
		
		Optional<Cliente> obj = repo.findByCpf(cpf);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! cpf: " + cpf + ", Tipo: " + Cliente.class.getName()));
	}

	public Cliente insert(Cliente obj) {
		
		obj.setId(null);
		obj = repo.save(obj);
		return obj;
	}
	
	public Cliente fromDto(ClienteNewDTO objDto) {
		
		Vendedor vend = vendedorService.findById(objDto.getVendedorId());
		Cliente obj = new Cliente(null, PadronizarNome.removeWhiteSpacesTogether(objDto.getNome()), objDto.getCpf(), Sexo.toEnum(objDto.getSexo()), vend);
		return obj;
	}
}
