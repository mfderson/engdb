package com.anderson.engdb.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.anderson.engdb.domain.Vendedor;
import com.anderson.engdb.dto.VendedorNewDTO;
import com.anderson.engdb.repositories.VendedorRepository;
import com.anderson.engdb.services.exceptions.ObjectNotFoundException;

@Service
public class VendedorService {

	@Autowired
	private VendedorRepository repo;
	
	public Vendedor findById(Integer id) {
		
		Optional<Vendedor> obj = repo.findById(id);		
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Vendedor.class.getName()));
	}
	
	public Vendedor findByCpf(String cpf) {
		
		Optional<Vendedor> obj = repo.findByCpf(cpf);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! cpf: " + cpf + ", Tipo: " + Vendedor.class.getName()));
	}

	public Vendedor insert(Vendedor obj) {
		obj.setId(null);
		obj = repo.save(obj);
		return obj;
	}

	public Vendedor fromDto(VendedorNewDTO objDto) {
		Vendedor obj = new Vendedor(null, objDto.getNome(), objDto.getCpf());
		return obj;
	}
}
