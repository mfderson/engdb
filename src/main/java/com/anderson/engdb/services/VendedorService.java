package com.anderson.engdb.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.anderson.engdb.domain.Vendedor;
import com.anderson.engdb.dto.VendedorNewDTO;
import com.anderson.engdb.dto.VendedorUpdtDTO;
import com.anderson.engdb.repositories.VendedorRepository;
import com.anderson.engdb.services.exceptions.ObjectNotFoundException;
import com.anderson.engdb.services.utils.PadronizarNome;

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

	@Transactional
	public Vendedor insert(Vendedor obj) {
		obj.setId(null);
		obj = repo.save(obj);
		return obj;
	}
	
	public Page<Vendedor> findAll(Pageable pageable) {
		return repo.findAll(pageable);
	}
	
	public Vendedor update(Vendedor obj) {
		Vendedor newObj = findById(obj.getId());
		updateData(newObj, obj);
		return repo.save(newObj);
	}
	
	public void delete(Integer id) {
		findById(id);
		repo.deleteById(id);
	}

	public Vendedor fromDto(VendedorNewDTO objDto) {
		Vendedor obj = new Vendedor(null, objDto.getNome().trim().replaceAll("\\s+", " "), objDto.getCpf());
		return obj;
	}

	public Vendedor fromDto(VendedorUpdtDTO objDto) {
		return new Vendedor(objDto.getId(), PadronizarNome.removeWhiteSpacesTogether(objDto.getNome()), null);
	}

	public Vendedor updateData(Vendedor newObj, Vendedor obj) {
		newObj.setNome(obj.getNome());		
		return null;
	}
}
