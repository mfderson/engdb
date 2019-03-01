package com.anderson.engdb.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.anderson.engdb.domain.Vendedor;
import com.anderson.engdb.repositories.VendedorRepository;

@Service
public class VendedorService {

	@Autowired
	private VendedorRepository repo;
	
	public Vendedor findById(Integer id) {
		
		Optional<Vendedor> obj = repo.findById(id);		
		return obj.orElse(null);
	}
	
	public Vendedor findByCpf(String cpf) {
		
		Optional<Vendedor> obj = repo.findByCpf(cpf);
		return obj.orElse(null);
	}
}
