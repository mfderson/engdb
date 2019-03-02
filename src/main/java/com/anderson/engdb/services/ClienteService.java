package com.anderson.engdb.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.anderson.engdb.domain.Cliente;
import com.anderson.engdb.repositories.ClienteRepository;
import com.anderson.engdb.services.exceptions.ObjectNotFoundException;

@Service
public class ClienteService {
	
	@Autowired
	private ClienteRepository repo;

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
}
