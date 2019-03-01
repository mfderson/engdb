package com.anderson.engdb.resources;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.anderson.engdb.domain.Vendedor;
import com.anderson.engdb.dto.VendedorNewDTO;
import com.anderson.engdb.services.VendedorService;

@RestController
@RequestMapping(value = "/vendedores")
public class VendedorResource {

	@Autowired
	private VendedorService service;
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Vendedor> findById(@PathVariable Integer id) {
		Vendedor obj = service.findById(id);
		return ResponseEntity.ok().body(obj);
	}
	
	@GetMapping(value = "/cpf")
	public ResponseEntity<Vendedor> findByCpf(@RequestParam(value = "value") String cpf) {
		Vendedor obj = service.findByCpf(cpf);
		return ResponseEntity.ok().body(obj);
	}
	
	@PostMapping
	public ResponseEntity<Void> insert(@Valid @RequestBody VendedorNewDTO objDto) {
		Vendedor obj = service.fromDto(objDto);
		obj = service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
}
